package com.codesquad.team3.baseball.controller;

import com.codesquad.team3.baseball.dto.ResponseData;
import com.codesquad.team3.baseball.dto.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mock/games/{gameId}/teams/{teamId}")
public class MockController {

    private static final Logger log = LoggerFactory.getLogger(MockController.class);

    @GetMapping("/records")
    public ResponseEntity<ResponseData> records(@PathVariable Integer gameId,
                                               @PathVariable Integer teamId) {
        String mockData = "{\n" +
                "    \"status\": \"SUCCESS\",\n" +
                "    \"content\": {\n" +
                "        \"isHome\": false,\n" +
                "        \"home\": {\n" +
                "            \"players\": [\n" +
                "                {\n" +
                "                    \"name\": \"김광진\",\n" +
                "                    \"order\": 1,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 0,\n" +
                "                    \"avg\": 1.1234\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"이동규\",\n" +
                "                    \"order\": 2,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 1,\n" +
                "                    \"avg\": 1.111\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"선수3\",\n" +
                "                    \"order\": 3,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 1,\n" +
                "                    \"avg\": 1.111\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"선수4\",\n" +
                "                    \"order\": 4,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 1,\n" +
                "                    \"avg\": 1.111\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"선수5\",\n" +
                "                    \"order\": 5,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 1,\n" +
                "                    \"avg\": 1.111\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"선수6\",\n" +
                "                    \"order\": 6,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 1,\n" +
                "                    \"avg\": 1.111\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"선수7\",\n" +
                "                    \"order\": 7,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 1,\n" +
                "                    \"avg\": 1.111\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"선수8\",\n" +
                "                    \"order\": 8,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 1,\n" +
                "                    \"avg\": 1.111\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"선수9\",\n" +
                "                    \"order\": 9,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 1,\n" +
                "                    \"avg\": 1.111\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"투수\",\n" +
                "                    \"order\": -1,\n" +
                "                    \"atBats\": -1,\n" +
                "                    \"hits\": -1,\n" +
                "                    \"outs\": -1,\n" +
                "                    \"avg\": 1.111\n" +
                "                }\n" +
                "            ],\n" +
                "            \"totals\": {\n" +
                "                \"atBats\": 9,\n" +
                "                \"hits\": 4,\n" +
                "                \"outs\": 5\n" +
                "            }\n" +
                "        },\n" +
                "        \"away\": {\n" +
                "            \"players\": [\n" +
                "                {\n" +
                "                    \"name\": \"김광진\",\n" +
                "                    \"order\": 1,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 0,\n" +
                "                    \"avg\": 1.1234\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"이동규\",\n" +
                "                    \"order\": 2,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 1,\n" +
                "                    \"avg\": 1.111\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"선수3\",\n" +
                "                    \"order\": 3,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 1,\n" +
                "                    \"avg\": 1.111\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"선수4\",\n" +
                "                    \"order\": 4,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 1,\n" +
                "                    \"avg\": 1.111\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"선수5\",\n" +
                "                    \"order\": 5,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 1,\n" +
                "                    \"avg\": 1.111\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"선수6\",\n" +
                "                    \"order\": 6,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 1,\n" +
                "                    \"avg\": 1.111\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"선수7\",\n" +
                "                    \"order\": 7,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 1,\n" +
                "                    \"avg\": 1.111\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"선수8\",\n" +
                "                    \"order\": 8,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 1,\n" +
                "                    \"avg\": 1.111\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"선수9\",\n" +
                "                    \"order\": 9,\n" +
                "                    \"atBats\": 1,\n" +
                "                    \"hits\": 1,\n" +
                "                    \"outs\": 1,\n" +
                "                    \"avg\": 1.111\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"투수\",\n" +
                "                    \"order\": -1,\n" +
                "                    \"atBats\": -1,\n" +
                "                    \"hits\": -1,\n" +
                "                    \"outs\": -1,\n" +
                "                    \"avg\": -1.111\n" +
                "                }\n" +
                "            ],\n" +
                "            \"totals\": {\n" +
                "                \"atBats\": 9,\n" +
                "                \"hits\": 4,\n" +
                "                \"outs\": 5\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        JsonNode jsonNode = null;
        try {
            jsonNode = new ObjectMapper().readTree(mockData);
        } catch (JsonProcessingException e) {
            log.debug("records: {}", e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, jsonNode), HttpStatus.OK);
    }

    @GetMapping("/scores")
    public ResponseEntity<ResponseData> scores(@PathVariable Integer gameId,
                                               @PathVariable Integer teamId) {
        String mockData = "{\n" +
                "  \"status\": \"SUCCESS\",\n" +
                "  \"content\": {\n" +
                "    \"isHome\": true,\n" +
                "    \"home\": {\n" +
                "      \"name\": \"롯데\",\n" +
                "      \"isAttack\": true,\n" +
                "      \"scores\": [\n" +
                "        1,\n" +
                "        0,\n" +
                "        0,\n" +
                "        0\n" +
                "      ],\n" +
                "      \"total\": 1\n" +
                "    },\n" +
                "    \"away\": {\n" +
                "      \"name\": \"삼성\",\n" +
                "      \"isAttack\": false,\n" +
                "      \"scores\": [\n" +
                "        1,\n" +
                "        2,\n" +
                "        2\n" +
                "      ],\n" +
                "      \"total\": 5\n" +
                "    }\n" +
                "  }\n" +
                "}";
        JsonNode jsonNode = null;
        try {
            jsonNode = new ObjectMapper().readTree(mockData);
        } catch (JsonProcessingException e) {
            log.debug("scores: {}", e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, jsonNode), HttpStatus.OK);
    }

}
