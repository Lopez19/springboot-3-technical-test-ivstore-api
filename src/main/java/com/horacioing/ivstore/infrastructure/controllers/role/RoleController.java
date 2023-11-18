package com.horacioing.ivstore.infrastructure.controllers.role;

import com.horacioing.ivstore.application.services.RoleService;
import com.horacioing.ivstore.domain.models.role.Role;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/roles")
@AllArgsConstructor
@Tag(name = "Roles", description = "Roles API")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(
            @RequestBody
            DataRoleRegister dataRegister,
            UriComponentsBuilder uriBuilder
    ) {
        Role roleSaved = this.roleService.createRole(
                Role.builder()
                        .name(dataRegister.name())
                        .build()
        );

        return ResponseEntity.created(
                uriBuilder
                        .path("api/v1/roles/{roleId}")
                        .buildAndExpand(roleSaved.getId())
                        .toUri()
        ).body(roleSaved);
    }

    @GetMapping
    public ResponseEntity<Page<Role>> getAllRoles(Pageable pagination) {
        return ResponseEntity.ok(this.roleService.getAllRoles(pagination));
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<Role> getRoleById(
            @PathVariable(name = "roleId")
            Long id
    ) {
        Optional<Role> roleFound = this.roleService.getRole(id);
        return roleFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
