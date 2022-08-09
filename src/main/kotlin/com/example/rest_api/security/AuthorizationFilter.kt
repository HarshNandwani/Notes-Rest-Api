package com.example.rest_api.security

import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTVerificationException
import com.example.rest_api.model.response.AuthFailedResponse
import com.example.rest_api.utils.Constants
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorizationFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (request.servletPath.equals("/auth/login") || request.servletPath.equals("/auth/register")) {
            filterChain.doFilter(request, response)
            return
        }

        val token = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (token == null || token.isEmpty()) {
            val res = AuthFailedResponse(Constants.ERR_EMPTY_TOKEN)
            response.status = HttpStatus.UNAUTHORIZED.value()
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            ObjectMapper().writeValue(response.outputStream, res)
            return
        }

        try {
            val jwtVerifier = JWT.require(Constants.jwtSigningAlgorithm).build()
            val decodedJWT = jwtVerifier.verify(token)
            val subject = decodedJWT.subject
            val authenticationToken = UsernamePasswordAuthenticationToken(subject, null, emptyList())
            SecurityContextHolder.getContext().authentication = authenticationToken
            filterChain.doFilter(request, response)
        } catch (e: JWTVerificationException) {
            val res = AuthFailedResponse(Constants.ERR_INVALID_TOKEN)
            // TODO: Check if we want to send Unauthorized 401 or Forbidden 403
            response.status = HttpStatus.UNAUTHORIZED.value()
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            ObjectMapper().writeValue(response.outputStream, res)
        }

    }
}
