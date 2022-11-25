http1 = buildHttpGet(\"https://www.qxwz.com/bffportal/proxy/cms/v2/api/datas/query?tag=portal-home-v2\");
http2 = buildHttpGet(\"https://www.qxwz.com/bffportal/proxy/omsconfigservice/api/hotProduct/open/list?channel=PC&isOperating=true\");
return parallelRequest(http1,http2);