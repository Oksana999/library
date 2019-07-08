package com.oksana.library.config;

import com.oksana.library.entities.Permission;
import com.oksana.library.entities.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class PermissionAspect {

    @Before("@annotation(com.oksana.library.config.PermissionRequired)")
    public void CheckPermission(JoinPoint joinPoint) throws Exception {
        User currentUser = UserHelper.getCurrentUser();
        if(currentUser.getRole().getId().equals(1L) || currentUser.getRole().getName().equals("admin")) {
            return;
        }

        Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
        String permissionName = method.getAnnotation(PermissionRequired.class).name();

//        boolean hasPermission = false;
//        for (Permission permission : currentUser.getRole().getPermissions()) {
//           if(permission.getName().equals(permissionName)) {
//                flaq = true;
//            }
//        }

        boolean hasPermission = currentUser.getRole().getPermissions()
                .stream()
                .anyMatch(x -> x.getName().equals(permissionName));


        if(!hasPermission) {
            throw new Exception("You do not have permission " + permissionName);
        }

    }

}
