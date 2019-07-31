package clientSide;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * LoginController Tester.
 *
 * @author zhangyu
 * @version 1.0
 * @since <pre>06/20/2019</pre>
 */
public class LoginControllerTest {
    private RestTemplate restTemplate = new RestTemplate();
    private String baseUrl = "http://localhost:8083";

    @Test
    public void testLoginCheckWithNotExistUser() throws Exception {
        String result = restTemplate.getForObject(
                baseUrl + "/login?userName=user001&password=pw001", String.class);
        assertNotNull(result);
        System.out.println(result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        Map<String, Object> metaMap = (Map<String,Object>)jsonObject.get("meta");
        assertEquals(-1, metaMap.get("errCode"));
    }

    @Test
    public void testLoginCheckWithExistUser() throws Exception {
        String result = restTemplate.getForObject(
                baseUrl + "/login?userName=aa&password=aa", String.class);
        assertNotNull(result);
        System.out.println(result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        Map<String, Object> metaMap2 = (Map<String,Object>)jsonObject.get("meta");
        assertEquals(0, metaMap2.get("errCode"));
    }


} 
