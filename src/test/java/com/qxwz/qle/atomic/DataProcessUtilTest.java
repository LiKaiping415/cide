package com.qxwz.qle.atomic;

import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.Test;

import java.util.Map;

class DataProcessUtilTest {

    @Test
    void equals1() {
        assert new DataProcessUtil().equals1("a","a") == true;
    }

    @Test
    void string2Json() {
        Map<String, Object> stringObjectMap = new DataProcessUtil().string2Json("{\n" +
                "    \"caller\": \"daily\",\n" +
                "    \"param\": {\n" +
                "        \"userId\": \"xianglei.zhang\",\n" +
                "        \"docId\": \"res:dw:qb:e981c4f1-2403-4b85-af85-4ebc3a561aa1\",\n" +
                "        \"isCollection\": false\n" +
                "    },\n" +
                "    \"requestId\": \"d538e47b-e2c0-4132-8db1-2a97f76ef3cc\"\n" +
                "}");
        System.out.println(stringObjectMap);
    }

    @Test
    void getValueFromJson() {
        System.out.println(new DataProcessUtil().getValueFromJson(new DataProcessUtil().string2Json("{\n" +
                "    \"caller\": \"daily\",\n" +
                "    \"param\": {\n" +
                "        \"userId\": \"xianglei.zhang\",\n" +
                "        \"docId\": \"res:dw:qb:e981c4f1-2403-4b85-af85-4ebc3a561aa1\",\n" +
                "        \"isCollection\": false\n" +
                "    },\n" +
                "    \"requestId\": \"d538e47b-e2c0-4132-8db1-2a97f76ef3cc\"\n" +
                "}"), new String[]{"param", "userId"}));
    }

    @Test
    void setValToJson() {
        DataProcessUtil dataProcessUtil = new DataProcessUtil();
        Map<String, Object> stringObjectMap = dataProcessUtil.string2Json("{\n" +
                "    \"caller\": \"daily\",\n" +
                "    \"param\": {\n" +
                "        \"userId\": \"xianglei.zhang\",\n" +
                "        \"docId\": \"res:dw:qb:e981c4f1-2403-4b85-af85-4ebc3a561aa1\",\n" +
                "        \"isCollection\": false\n" +
                "    },\n" +
                "    \"requestId\": \"d538e47b-e2c0-4132-8db1-2a97f76ef3cc\"\n" +
                "}");
        System.out.println(dataProcessUtil.setValToJson(stringObjectMap, new String[]{"param", "opUser"}, "lkp"));
    }

    @Test
    void json2String() {
        DataProcessUtil dataProcessUtil = new DataProcessUtil();
        Map<String, Object> stringObjectMap = dataProcessUtil.string2Json("{\n" +
                "    \"caller\": \"daily\",\n" +
                "    \"param\": {\n" +
                "        \"userId\": \"xianglei.zhang\",\n" +
                "        \"docId\": \"res:dw:qb:e981c4f1-2403-4b85-af85-4ebc3a561aa1\",\n" +
                "        \"isCollection\": false\n" +
                "    },\n" +
                "    \"requestId\": \"d538e47b-e2c0-4132-8db1-2a97f76ef3cc\"\n" +
                "}");
        System.out.println(dataProcessUtil.json2String(stringObjectMap));
    }

    @Test
    void addJsonToJson() {
        DataProcessUtil dataProcessUtil = new DataProcessUtil();
        Map<String, Object> stringObjectMap = dataProcessUtil.string2Json("{\n" +
                "    \"caller\": \"daily\",\n" +
                "    \"param\": {\n" +
                "        \"userId\": \"xianglei.zhang\",\n" +
                "        \"docId\": \"res:dw:qb:e981c4f1-2403-4b85-af85-4ebc3a561aa1\",\n" +
                "        \"isCollection\": false\n" +
                "    },\n" +
                "    \"requestId\": \"d538e47b-e2c0-4132-8db1-2a97f76ef3cc\"\n" +
                "}");
        Map<String, Object> stringObjectMap2 = dataProcessUtil.string2Json("{\n" +
                "    \"caller\": \"daily\",\n" +
                "    \"param\": {\n" +
                "        \"userId\": \"xianglei.zhang\",\n" +
                "        \"docId\": \"res:dw:qb:e981c4f1-2403-4b85-af85-4ebc3a561aa1\",\n" +
                "        \"isCollection\": false\n" +
                "    },\n" +
                "    \"requestId\": \"d538e47b-e2c0-4132-8db1-2a97f76ef3cc\"\n" +
                "}");
        System.out.println(dataProcessUtil.addJsonToJson(stringObjectMap, new String[]{"param", "inner"}, stringObjectMap2));
    }

    @Test
    void concatJson2Json() {
        DataProcessUtil dataProcessUtil = new DataProcessUtil();
        Map<String, Object> stringObjectMap = dataProcessUtil.string2Json("{\n" +
                "    \"caller\": \"daily\",\n" +
                "    \"param\": {\n" +
                "        \"userId\": \"xianglei.zhang\",\n" +
                "        \"docId\": \"res:dw:qb:e981c4f1-2403-4b85-af85-4ebc3a561aa1\",\n" +
                "        \"isCollection\": false\n" +
                "    },\n" +
                "    \"requestId\": \"d538e47b-e2c0-4132-8db1-2a97f76ef3cc\"\n" +
                "}");
        Map<String, Object> stringObjectMap2 = dataProcessUtil.string2Json("{\n" +
                "    \"caller1\": \"daily\",\n" +
                "    \"param1\": {\n" +
                "        \"userId\": \"xianglei.zhang\",\n" +
                "        \"docId\": \"res:dw:qb:e981c4f1-2403-4b85-af85-4ebc3a561aa1\",\n" +
                "        \"isCollection\": false\n" +
                "    },\n" +
                "    \"requestId1\": \"d538e47b-e2c0-4132-8db1-2a97f76ef3cc\"\n" +
                "}");
        System.out.println(dataProcessUtil.concatJson2Json(stringObjectMap, stringObjectMap2));
    }

    @Test
    void getJsonArrayFromJson() {
        DataProcessUtil dataProcessUtil = new DataProcessUtil();
        Map<String, Object> stringObjectMap = dataProcessUtil.string2Json("{\"param\": {\n" +
                "        \"staffNo\": \"v-kaiping.li\",\n" +
                "        \"staffName\": \"李凯平\",\n" +
                "        \"qbiResAccessList\": [\n" +
                "            {\n" +
                "                \"documentId\": \"res:dw:report:35d2af59-25e8-4f09-ab09-0a5dbde4ffa0\",\n" +
                "                \"reportId\": \"35d2af59-25e8-4f09-ab09-0a5dbde4ffa0\",\n" +
                "                \"reportName\": \"创建dsk明细报表（新购/扩容）\",\n" +
                "                \"reportPath\": \"客户服务空间/智驾对账汇总/智驾对账&汇总/其他\",\n" +
                "                \"reportOwner\": \"jiaxing.wu\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"resDuration\": {\n" +
                "            \"timeScale\": 2,\n" +
                "            \"timeUnit\": \"month\",\n" +
                "            \"timeDesc\":\"两个月\"\n" +
                "        },\n" +
                "        \"opAuthority\": \"view\",\n" +
                "        \"applyDesc\": \"test\"\n" +
                "    }}");
        System.out.println(dataProcessUtil.getJsonArrayFromJson(stringObjectMap, new String[]{"param", "qbiResAccessList"}));
    }

    @Test
    void getJsonFromJsonArrayByIndex() {
        DataProcessUtil dataProcessUtil = new DataProcessUtil();
        Map<String, Object> stringObjectMap = dataProcessUtil.string2Json("{\"param\": {\n" +
                "        \"staffNo\": \"v-kaiping.li\",\n" +
                "        \"staffName\": \"李凯平\",\n" +
                "        \"qbiResAccessList\": [\n" +
                "            {\n" +
                "                \"documentId\": \"res:dw:report:35d2af59-25e8-4f09-ab09-0a5dbde4ffa0\",\n" +
                "                \"reportId\": \"35d2af59-25e8-4f09-ab09-0a5dbde4ffa0\",\n" +
                "                \"reportName\": \"创建dsk明细报表（新购/扩容）\",\n" +
                "                \"reportPath\": \"客户服务空间/智驾对账汇总/智驾对账&汇总/其他\",\n" +
                "                \"reportOwner\": \"jiaxing.wu\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"resDuration\": {\n" +
                "            \"timeScale\": 2,\n" +
                "            \"timeUnit\": \"month\",\n" +
                "            \"timeDesc\":\"两个月\"\n" +
                "        },\n" +
                "        \"opAuthority\": \"view\",\n" +
                "        \"applyDesc\": \"test\"\n" +
                "    }}");
        JSONArray jsonArrayFromJson = dataProcessUtil.getJsonArrayFromJson(stringObjectMap, new String[]{"param", "qbiResAccessList"});
        System.out.println(dataProcessUtil.getJsonFromJsonArrayByIndex(jsonArrayFromJson,0));
    }
}