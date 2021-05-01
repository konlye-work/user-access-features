package com.features.api;

import com.features.api.utils.Validator;
import com.google.gson.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc
class UserAccessFeaturesApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    private Gson gson;

    @BeforeEach
    void setup() {
        gson = new Gson();
    }

    @Test
    public void validateEmail(){
        assertTrue(Validator.validEmail("test1@gmail.com"));
        assertFalse(Validator.validEmail("test1%@$@gmail.com"));
    }

    @Test
    public void doGetTest() throws Exception {
        MockHttpServletRequestBuilder mockBuilder = MockMvcRequestBuilders.request(HttpMethod.GET, "/feature?email=test1@gmail.com&featureName=feature1");
        JsonObject response =  gson.fromJson(JsonParser.parseString(mockMvc.perform(mockBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn()
                .getResponse().getContentAsString()), JsonObject.class);
        assertEquals(response.get("canAccess").getAsBoolean(), true);
    }

    @Test
    public void doPostTest() throws Exception {
        JsonObject postObject = new JsonObject();
        postObject.addProperty("featureName","feature2");
        postObject.addProperty("email","test2@gmail.com");
        postObject.addProperty("enable",true);
        MockHttpServletRequestBuilder mockBuilder = MockMvcRequestBuilders.request(HttpMethod.POST, "/feature")
                .content(gson.toJson(postObject,JsonObject.class))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);


        gson.fromJson(JsonParser.parseString(mockMvc.perform(mockBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn()
                .getResponse().getContentAsString()), JsonNull.class);
    }
}