package com.jhta.bonfire;

import static org.junit.Assert.assertFalse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/security-config.xml" })
// @WebAppConfiguration
public class TestBoard {
    // @Autowired WebApplicationContext ctx;
    // @Autowired private SubBoardService service;
    Logger logger = LoggerFactory.getLogger(getClass());
    // private MockMvc mockMVC;
    // @Before
    // public void setUp() {
    // mockMVC = MockMvcBuilders.webAppContextSetup(ctx).build();
    // }
    HashMap<String, Object> map = new HashMap<>();

    @Before
    public void setup() {
        map.put("startRow", 1);
        map.put("endRow", 10);
        List<String> fields = new ArrayList<String>();
        fields.add("id");
        fields.add("content");

        map.put("cname", "ㅁ");
        map.put("fields", fields);
        // map.put("field", "content");
        map.put("keyword", "id");
    }

    // @Test public void cname(){
    // List<String> list = service.catList("subscriberboard");
    // list.forEach(e->{
    // logger.info(e.toString());
    // });
    // assertTrue(!list.isEmpty());
    // }

    // @Test public void counttest() {
    // int n = service.count(map);
    // logger.info("----->"+n);
    // assertTrue(n==2);
    // }

    // @Test public void listtest(){
    // List<SubBoardVo> list = service.getList(map);
    // for (SubBoardVo subBoardVo : list) {
    // logger.info("INFO : "+subBoardVo.toString());
    // }
    // logger.info("size:"+list.size());
    // assertNotNull(list);
    // }

    // @Test public void addhittest(){
    // SbhitsVo vo = new SbhitsVo(10000024, "id");
    // logger.info("aaaaaa"+vo.toString());
    // int n = service.addHit(vo);
    // assertSame(0, n);
    // }

    @Test
    public void commenttest() {
        String context = "";
        String boardName = "bbbbbbb";
        String changed = context.replaceAll(
                "(src=\\\"/bonfire/resources/)(TemporalFileStorage)(/.{36}[.]\\w{1,}\\\"><br></p>)",
                "$1" + boardName + "$3");
        // logger.info(context);
        logger.info("changed: " + changed);
        assertFalse(changed.contains("TemporalFileStorage"));
    }

    @Test
    public void testmap() {
        String strUrl = "http://api.data.go.kr/openapi/tn_pubr_public_trrsrt_api";
        strUrl += "?serviceKey=KeojhMqOe8z3LzsYB5ymIQPUj5749Uum582lLmejjYb04ZX33PBrTSMh%2FMt%2BgdFF8nnXhnE%2FBuviGPbZicaxHg%3D%3D";
        strUrl += "&type=json";
        strUrl += "&pageNo=0";
        strUrl += "&numOfRows=1";
        // strUrl += "&instt_nm=UTF-8로 인코딩된 value";
        URL url;
        URLConnection urlConnection;
        try {
            url = new URL(strUrl);
            urlConnection = url.openConnection();

            HttpURLConnection connection = null;
            if (urlConnection instanceof HttpURLConnection) {
                connection = (HttpURLConnection) urlConnection;
            } else {
                System.out.println("error");
                return;
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String urlString = "";
            String current;
            while ((current = in.readLine()) != null) {
                urlString += current + "\n";
            }
            logger.info(urlString);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // #region 네이버검색
    // 좌표를 통한 주변검색은 불가능
    @Test
    public void naversearchtest() {
        String clientId = "phn01d3V5jyTnFVM_33K";
        String clientSecret = "_n6a7qROSq";
        String searchArea = "포항시";
        try {
            searchArea = URLEncoder.encode(searchArea + " 맛집", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }
        String apiURL = "https://openapi.naver.com/v1/search/local?query=" + searchArea;

        int display = 10;
        int start = 1; // default
        // int start+=display

        Map<String, Object> params = new HashMap<>();
        params.put("display", display);
        params.put("start", start);

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL, requestHeaders);

        logger.info(responseBody);
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    // #endregion
    // conn.setRequestProperty("Authorization", "KakaoAK " + this.adminKey);
    @Test
    public void kakaoMapTestCategoryAndKeyword() {
        // String httpUrl = "https://httpbin.org/get";
        String httpUrl = "https://dapi.kakao.com/v2/local/search/keyword.json";
        RestTemplate restTemplate = new RestTemplate();
        //이게 없으면 401에러가 뜸.
        // HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        // requestFactory.setConnectTimeout(30000);
        // DefaultResponseErrorHandler errorHandler = new DefaultResponseErrorHandler(){
        //     public boolean hasError(ClientHttpResponse response) throws IOException {
        //         HttpStatus statusCode=response.getStatusCode();
        //         return statusCode.series()==HttpStatus.Series.SERVER_ERROR;
        //         //에러나면 true 아니면 false 리턴
        //     }
        // };
        // restTemplate.setRequestFactory(requestFactory);
        // restTemplate.setErrorHandler(errorHandler);
        // 아님말고..

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        // 동일한 key를 가진 여러 value들이 존재할 수 있는 Map
        // TODO:실제로 사용시 이렇게 하지 않고 CommonUtil에 Converter 메소드를 만들어서 넣어주면 좋음
        params.add("page", "1");
        params.add("size", "15");
        params.add("sort", "distance");
        params.add("radius", "2000");
        params.add("x", "126.978734");
        params.add("y", "37.494301");
        params.add("category_group_code", "FD6");
        params.add("query", "밥");
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(httpUrl).queryParams(params);
        URI uri = builder.build().encode().toUri();
        //get 요청을 URL로 변환함
        logger.info("URI:----------------------------\n"+uri.toString());
        //CURL에서 Http 인증에 사용할 헤더를 만듬. curl -H 이후에 들어가는 파라미터
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK "+"ddc296c2f9f2c09e7b1c05df318b9cb0");
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<JSONObject> result = restTemplate.exchange(uri, HttpMethod.GET, entity, JSONObject.class);
        logger.info(result.getBody().toJSONString());

    }
    /* 
curl -X GET "https://dapi.kakao.com/v2/local/search/keyword.json?page=1&size=15&sort=distance&radius=2000&x=126.978734&y=37.494301&category_group_code=FD6&query=%EB%B0%A5" \
-H "Authorization: KakaoAK ddc296c2f9f2c09e7b1c05df318b9cb0"
    */
    @Test public void kakaoCoord2Address(){
        String httpUrl = "https://dapi.kakao.com/v2/local/geo/coord2address.json";
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("x", "126.978734");
        params.add("y", "37.494301");
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(httpUrl).queryParams(params);
        URI uri = builder.build().encode().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK "+"ddc296c2f9f2c09e7b1c05df318b9cb0");
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<JSONObject> result = restTemplate.exchange(uri, HttpMethod.GET, entity, JSONObject.class);
        logger.info(result.getBody().toJSONString());
    }

    @Test public void kakaoAddress2Coord(){
        String httpUrl = "https://dapi.kakao.com/v2/local/search/address.json";
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", "서울 종로구 율곡로10길 105");
        params.add("page", "1");
        params.add("size", "10");
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(httpUrl).queryParams(params);
        URI uri = builder.build().encode().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK "+"ddc296c2f9f2c09e7b1c05df318b9cb0");
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<JSONObject> result = restTemplate.exchange(uri, HttpMethod.GET, entity, JSONObject.class);
        logger.info(result.getBody().toJSONString());
    }
    @Test public void testMap(){
        Map<String, Object> geometry= new HashMap<>();
        int[] a = {1, 2};
        geometry.put("type", "value");
        geometry.put("coordinates", a);
        logger.info("tostring", geometry.get("type"));
        logger.info("geometryget", geometry.get("coordinates"));
    }
}