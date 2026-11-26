package com.sushmitha.bugtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sushmitha.bugtracker.dto.CreateBugRequest;
import com.sushmitha.bugtracker.model.Bug;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest @AutoConfigureMockMvc @WithMockUser
class BugControllerTest {
    @Autowired MockMvc mvc; @Autowired ObjectMapper mapper;
    @Test void rejectsBlankTitle() throws Exception {
        var request = new CreateBugRequest("", "description", Bug.Status.OPEN, Bug.Priority.HIGH);
        mvc.perform(post("/api/bugs").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request))).andExpect(status().isBadRequest());
    }

    @Test void createsValidBug() throws Exception {
        var request = new CreateBugRequest("Login timeout", "Users see a timeout after sign-in.", Bug.Status.OPEN, Bug.Priority.HIGH);
        mvc.perform(post("/api/bugs").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request))).andExpect(status().isCreated());
    }

    @Test void returnsNotFoundForMissingBug() throws Exception {
        mvc.perform(get("/api/bugs/999999")).andExpect(status().isNotFound());
    }
}
