package com.cydeo.tests.day14_specifications;

import com.cydeo.utils.BookItAPITestBase;
import org.junit.jupiter.api.Test;

public class BookItSpecTest extends BookItAPITestBase {
    /**
     * Given accept type is json
     * And header Authorization is access_token of teacher
     * When I send GET request to /api/teachers/me
     * Then status code is 200
     * And content type is json
     * And teacher info is presented in payload
     */
    @Test
    public void teacherInfoTest() {

    }
}