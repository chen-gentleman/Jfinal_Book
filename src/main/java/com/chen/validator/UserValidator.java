package com.chen.validator;

import com.chen.entity.User;
import com.chen.service.UserService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class UserValidator extends Validator {
    @Inject
    private UserService userService;
    @Override
    protected void validate(Controller controller) {
        validateRequiredString("user.userName", "nameMsg", "请输入用户名!");
        validateRequiredString("user.passWord", "passwordMsg", "请输入密码!");

        String userName = controller.getPara("user.userName");
        //System.out.println(userName);
        if (userService.userNameisExists(userName)) {
            //System.out.println("进入if");
            //validateRequiredString("user.userName", "errorMsg", "用户名已被注册，请使用别的用户名！!");
            addError("errorMsg", "用户名已被注册，请使用别的用户名！!");
        }
    }

    /**
     * 注意handleError(Controller c)只有在校验失败时才会调用。
     * @param controller
     */
    @Override
    protected void handleError(Controller controller) {
       // controller.keepModel(User.class);
        //controller.keepBean(User.class);
        controller.keepPara("user.userName");
        controller.keepPara("user.passWord");
        /**
         * 获取执行的方法名
         */
        String actionKey = getActionKey();
        if (actionKey.equals("/user/verification"))
            controller.render("login.html");
        else if (actionKey.equals("/user/save"))
            controller.render("register.html");
    }
}
