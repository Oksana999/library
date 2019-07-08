package com.oksana.library.config;

import com.oksana.library.entities.Permission;
import com.oksana.library.services.PermissionService;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PermissionInitializer {

    private final PermissionService permissionService;

    @PostConstruct
    public void init() {
        Set<Method> methods = this.getAllMethodsFromProject();
        List<Permission> permissionsInDatabase = permissionService.findAll();
        List<Permission> permissionsInProject = this.getPermissionsInProject(methods);

        this.saveNewPermissions(permissionsInProject, permissionsInDatabase);
        permissionsInDatabase = permissionService.findAll();
        this.removeOldPermissions(permissionsInDatabase, permissionsInProject);
    }

    private List<Permission> getPermissionsInProject(Set<Method> methods) {
        List<Permission> result = new ArrayList<>();
        for (Method method : methods) {
            PermissionRequired annotation = (PermissionRequired) Arrays.stream(method.getAnnotations())
                    .filter(x -> x instanceof PermissionRequired)
                    .findFirst().get();
            result.add(getPermission(annotation));
        }
        return result;
    }

    private void saveNewPermissions(List<Permission> permissionsInProject, List<Permission> permissionsInDatabase) {
        for (Permission permission : permissionsInProject)
            if (permissionsInDatabase.stream().noneMatch(x -> x.getName().equals(permission.getName())))
                permissionService.save(permission);
    }

    private void removeOldPermissions(List<Permission> permissionsInDatabase, List<Permission> permissionsInProject) {
        List<Permission> unnecessaryPermissionsInDatabase = new ArrayList<>(permissionsInDatabase);
        unnecessaryPermissionsInDatabase.removeAll(permissionsInProject);
        unnecessaryPermissionsInDatabase.forEach(permissionService::delete);
    }

    private Set<Method> getAllMethodsFromProject() {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder().setUrls(
                        ClasspathHelper.forPackage("com.oksana")).setScanners( //todo how to get non-magic constant of project package?
                        new MethodAnnotationsScanner()));
        return reflections.getMethodsAnnotatedWith(PermissionRequired.class);
    }

    private Permission getPermission(PermissionRequired annotation) {
        Permission result = new Permission();
        result.setName(annotation.name());
        return result;
    }
}
