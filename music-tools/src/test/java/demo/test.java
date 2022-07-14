package demo;

import org.ansj.splitWord.analysis.*;
import org.junit.jupiter.api.Test;

public class test {
   @Test
    void demo01(){
      String content = "首次设计不是来自M78星云的战士。但是拍摄追求恐怖和悲伤的效果以及剧情与前作的迥然不同，使得观众并不适应，中后期尝试转变风格，但日本收视率依然不济，也使得圆谷前期的辉煌成就作古。";
      //最小细粒度分词
      System.out.println(BaseAnalysis.parse(content));
      //精准分词
      System.out.println(ToAnalysis.parse(content));
      //用户自定义词典优先分词
      System.out.println(DicAnalysis.parse(content));
      //面向索引的分词
      System.out.println(IndexAnalysis.parse(content));
      //带有新词发现的分词
      System.out.println(NlpAnalysis.parse(content));
   }
}
