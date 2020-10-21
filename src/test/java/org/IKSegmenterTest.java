package org;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import java.io.IOException;
import java.io.StringReader;


/**
 * Created by yuewang.chen1@nio.com on 2020/10/16.
 */
public class IKSegmenterTest {
    static String text = "中华人民共和国";

    public static void main(String[] args) throws IOException {
        Settings settings = Settings.builder()
                .put("path.home","/Users/yuewang.chen1/CMPro/elasticsearch-analysis-ik/")
                .build();
        Environment env = new Environment(settings, null);
        Configuration configuration = new Configuration(env, settings);
        configuration.setUseSmart(false);
        IKSegmenter segmenter = new IKSegmenter(new StringReader(text), configuration);
        Lexeme next;
        System.out.print("非智能分词结果：");
        while ((next=segmenter.next())!=null) {
            System.out.print(next.getLexemeText()+"  ");
        }

        System.out.println();
        System.out.println("----------------------------分割线------------------------------");
        configuration.setUseSmart(true);
        IKSegmenter smartSegmenter = new IKSegmenter(new StringReader(text), configuration);
        System.out.print("智能分词结果：");
        while((next=smartSegmenter.next())!=null) {
            System.out.print(next.getLexemeText() + "  ");
        }
    }
}
