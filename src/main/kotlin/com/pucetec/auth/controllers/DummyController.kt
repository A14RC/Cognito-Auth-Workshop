package com.pucetec.auth.controllers

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class DummyController {

    @GetMapping("/public")
    fun public(): Response {
        return Response("Todo ok. Este es un endpoint publico")
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/restricted")
    fun restricted(
        @AuthenticationPrincipal jwt: Jwt?,
    ): Response {
        val user = jwt?.getClaim<String>("username") ?: "usuario"
        return Response("Todo Ok. Este es un endpoint para usuarios autenticados. Hola ${user}")
    }

    @PreAuthorize("hasAuthority('ROLE_admin')")
    @GetMapping("/restricted/admin")
    fun admin(): Response {
        return Response("Todo Ok. Este es un endpoint solo para admins")
    }

    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_manager')")
    @GetMapping("/restricted/sensitive")
    fun sensitiveData(): Response {
        return Response("Todo Ok. Este es un endpoint solo para admins y managers")
    }

    @PreAuthorize("hasAnyRole('ROLE_manager')")
    @GetMapping("/restricted/manager")
    fun managerOnly(): Response {
        return Response("Todo Ok. Este es un endpoint solo managers")
    }
}

data class Response(val message: String)