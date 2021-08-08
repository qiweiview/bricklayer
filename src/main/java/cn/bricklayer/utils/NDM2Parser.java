package cn.bricklayer.utils;

import com.fasterxml.jackson.databind.JsonNode;

public class NDM2Parser {

    public static NDM2Server ofString(String json) {

        JsonNode jsonNode = Jackson.readTree(json);

        JsonNode server = jsonNode.findValue("server");

        NDM2Server ndm2Server = Jackson.nodeToObject(server, NDM2Server.class);

        return ndm2Server;
    }

}
