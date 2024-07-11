import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JsonWriter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File file = new File("news.json");

    public void write(Set<News> newsSet) {

        // 1) 먼저 news.json을 가져와

        // 2) Map으로 가져와
        // key - newUrl, value - newsText
        Map<String, String> existingNews = new HashMap<>();

        try {
            if (file.exists()) {
                existingNews = objectMapper.readValue(file, new TypeReference<Map<String, String>>() {
                });
            }

            // 3) 우리가 메모리에 있는 Set<News>를 이제 map에다가 넣어준다.
            for (News news : newsSet) {
                existingNews.put(news.newsUrl(), news.newsText());
            }

            objectMapper.writeValue(file, existingNews);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 파일이 존재하면 기존 데이터를 읽어오기


        // 4) Map의 특징 -> Key값이 중복되면 그거는 그냥 덮어쓰기 되잖아.

        // a.naver.com - 마이 뉴스
        // b.naver.com - 마이 뉴스
        // c.naver.com - 마이 뉴스
        // d.naver.com - 마이 뉴스

        // 5) 이 map을 news.json으로 저장만 해주면


    }
}
