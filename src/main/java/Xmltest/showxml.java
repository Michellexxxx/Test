package Xmltest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class showxml {
    static Document document=null;
    static Element root=null;
    static Scanner input=new Scanner(System.in);
    public static void main(String[] args) {
        String answ;
        do {
            System.out.println("************欢迎进入王者荣耀操作*************");
            System.out.println("***********请输入你的选择**************");
            System.out.println("************1.查询*************");
            System.out.println("************2.修改*************");
            System.out.println("************3.增加*************");
            System.out.println("************4.删除*************");
            getDocument();
            int choose=input.nextInt();
            switch (choose){
                case 1:
                    findAll();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    add();
                    break;
                case 4:
                    delete();
                    break;
            }
            System.out.println("是否继续？（y/n）");
            answ=input.next();

        }while (answ.equals("是")||answ.equals("y"));


    }

    private static void delete() {
        Iterator<Element> classes=root.elementIterator();
        System.out.println("选择要删除的内容");
         String message=input.next();
        while(classes.hasNext()){
            Element grade=classes.next();
            if (grade.attributeValue("name").equals(message)){
                root.remove(grade);
                System.out.println("删除成功！！");
            }

        }
        saveXml();
    }

    private  static  void saveXml(){
        XMLWriter writer=null;
        OutputFormat format=null;
        try {
            format=OutputFormat.createPrettyPrint();
            writer=new XMLWriter(new FileWriter("src/main/resources/class.xml"),format);
            writer.write(document);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void getDocument() {
        SAXReader reader=new SAXReader();

        try {
            document=reader.read("src/main/resources/class.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        root=document.getRootElement();
    }
    private static void update() {
        Iterator<Element> classs=root.elementIterator();
        while(classs.hasNext()){
            Element clas=classs.next();
            Iterator<Element> student=clas.elementIterator();
            while(student.hasNext()){
                Element stu=student.next();
                if(stu.getText().equals("小黑2")){
                    stu.setText("小黑黑");
                }
            }
        }
        saveXml();
    }
    private static void add() {
        Element classe=root.addElement("name");
        System.out.println("请输入添加的班级：");
        String classes=input.next();
        classe.addAttribute("name",classes);

        Element student=classe.addElement("id");
        student.addAttribute("id","1");
        student.setText("小红1");

        Element students2=classe.addElement("id");

        students2.addAttribute("id","2");
        students2.setText("小红2");
        saveXml();
    }

    private static void findAll() {
        Iterator<Element> classes=root.elementIterator();
        while (classes.hasNext()){
            Element clas=classes.next();
            System.out.println("班级的名称是"+clas.attributeValue("name"));

            Iterator<Element> claset=clas.elementIterator();
            while (claset.hasNext()){
                Element clazz=claset.next();

                System.out.println("班级名称："+clazz.attributeValue("id")+clazz.getText());
            }
        }
    }
}
