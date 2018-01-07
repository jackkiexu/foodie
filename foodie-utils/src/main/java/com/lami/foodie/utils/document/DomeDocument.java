package com.lami.foodie.utils.document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.lami.foodie.utils.json.GsonUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * 参考资料
 *
 * Created by xujiankang on 2017/8/17.
 */
public class DomeDocument {

    private static final Logger logger = Logger.getLogger(DomeDocument.class);

    public static void main(String[] args) throws Exception{
        // 创建解析器工厂实例, 并生成解析器
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // 创建需要解析的文档对象
        URL url = DomeDocument.class.getClassLoader().getResource("books.xml");
        File f = new File(url.getPath());
        // 解析文档, 并返回一个 Document 对象, 此时 xml 文档以加载到内存中
        Document doc = builder.parse(f);

        // 获取 文档的根元素
        Element root = doc.getDocumentElement();

        // 上面找到了根节点, 这里开始获取根节点下的元素集合
        NodeList list = root.getElementsByTagName("book");

        for(int i = 0; i < list.getLength(); i++){
            // 通过 item() 方法找到集合中的节点, 并向下转型为 Element 对象
            Element n = (Element)list.item(i);
            // 获取 对象中的属性 map, 用 for 循环提取并打印出来
            NamedNodeMap node = n.getAttributes();
            for(int x = 0; x < node.getLength(); x++){
                Node nn = node.item(x);
                if(nn != null )logger.info(nn.getNodeName() + ": " + nn.getNodeValue());
            }

            // 打印元素内容, 代码很纠结, 差不多是个固定格式
            logger.info("title: " + n.getElementsByTagName("title").item(0).getFirstChild().getNodeValue());
            logger.info("author: " + n.getElementsByTagName("author").item(0).getFirstChild().getNodeValue());
            logger.info("");
        }

    }

}
