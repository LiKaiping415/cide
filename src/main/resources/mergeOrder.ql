request1 = buildHttpPost("rest.qxwz-test4.web.wz-inc.com/order-backend/rest/order-detail-query");
request1 = setHttpPostBody(request1, json2String(originBody));

request3 = buildHttpPost("rest.qxwz-test4.web.wz-inc.com/saturn-backend/rest/query-used-coupons");
request3 = setHttpPostBody(request3, json2String(originBody));

orderNo = getValueFromJson(originBody, ["param", "orderNo"]);
newBodyJson = setValToJson(originBody, ["param", "tradeOrderNo"], orderNo);
request5 = buildHttpPost("rest.qxwz-test4.web.wz-inc.com/adam/rest/query-trade-order-logistics");
request5 = setHttpPostBody(request5, json2String(newBodyJson));

result1 = parallelRequest(["orderDetail", "coupons", "trade"], [request1, request3, request5]);

userId = getValueFromJson(result1, ["orderDetail", "data", "userId"]);
request2 = buildHttpPost("http://rest.qxwz-test4.web.wz-inc.com/venusbizservice/user/getUserDetail");
request2 = setHttpPostBody(request2, json2String(setValToJson(originBody, ["param", "userId"], userId)));

orderId = getValueFromJson(result1, ["orderDetail", "data", "orderId"]);
request4 = buildHttpPost("http://rest.qxwz-test4.web.wz-inc.com/orderapi/rest/query-order-si");
request4 = setHttpPostBody(request4, json2String(setValToJson(originBody, ["param", "orderId"], orderId)));

result2 = parallelRequest(["userDetail", "si"], [request2, request4]);

skuArray = getJsonArrayFromJson(result2, ["si", "data", "list"]);
skuIdList = new ArrayList();
skuRequestList = new ArrayList();
for (int i=0;i<skuArray.size();i++) {
    skuId = getValueFromJson(getJsonFromJsonArrayByIndex(skuArray, i), ["skuId"])
    if(skuId != null){
        skuIdList.add(skuId);
        request = buildHttpPost("http://rest.qxwz-test4.web.wz-inc.com/orderapi/rest/query-order-si");
        request = setHttpPostBody(request, json2String(setValToJson(originBody, ["param", "skuId"], skuId)));
        skuRequestList.add(request);
    }
}
result3 = parallelRequestList(skuIdList, skuRequestList);

return concatJson2Json(concatJson2Json(result1,result2),result3);



