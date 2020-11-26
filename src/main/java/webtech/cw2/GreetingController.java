package webtech.cw2;


import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class GreetingController {

    @RequestMapping(value ="/")
    public String greeting(Model model) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder() .url("https://api.coronavirus.data.gov.uk/v1/data?filters=areaType=nation;areaName=england&structure=%7B%22case%22:%22newCasesByPublishDate%22,%22date%22:%22date%22%7D") .get().build();


        Response response = client.newCall(request).execute();
        JSONObject myObject = new JSONObject(response.body().string());

        JSONArray jsonArray = myObject.getJSONArray("data");
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        Integer cases = (Integer) jsonObject.get("case");

        model.addAttribute("test",cases);






        return "greeting";
    }

}
