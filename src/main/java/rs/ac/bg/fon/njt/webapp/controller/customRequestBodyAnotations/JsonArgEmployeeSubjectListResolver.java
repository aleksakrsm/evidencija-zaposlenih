/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.controller.customRequestBodyAnotations;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeSubjectDto;

/**
 *
 * @author aleks
 */
public class JsonArgEmployeeSubjectListResolver implements HandlerMethodArgumentResolver {

    private static JsonNode rootNodeStatic = null;

    private ObjectMapper objectMapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JsonArgEmployeeSubjectList.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        if (servletRequest == null) {
            throw new IllegalStateException("Current request is not an HttpServletRequest");
        }
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
//        objectMapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(servletRequest.getInputStream());
            rootNodeStatic = rootNode;
        } catch (Exception e) {
            rootNode = rootNodeStatic;
        }
        String jsonPath = Objects.requireNonNull(
                Objects.requireNonNull(parameter.getParameterAnnotation(JsonArgEmployeeSubjectList.class)).path());
        JsonNode valueNode = rootNode.at(jsonPath);
        String myParamJson = objectMapper.writeValueAsString(valueNode);
        return objectMapper.readValue(myParamJson, objectMapper.getTypeFactory().constructCollectionType(List.class, EmployeeSubjectDto.class));
    }
}
