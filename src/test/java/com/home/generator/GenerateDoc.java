//package com.home.generator;
//
//import io.github.swagger2markup.Swagger2MarkupConfig;
//import io.github.swagger2markup.Swagger2MarkupConverter;
//import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
//import io.github.swagger2markup.markup.builder.MarkupLanguage;
//import org.asciidoctor.cli.AsciidoctorInvoker;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.net.URL;
//import java.nio.file.Paths;
//
//
////asciidoctor:process-asciidoc
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//public class GenerateDoc {
//
//    @Test
//    public void generateAsciiDocs() throws Exception {
//        //    输出Ascii格式
//        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
//                .withGeneratedExamples()
//                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
//                .build();
//
//        Swagger2MarkupConverter.from(new URL("http://localhost/article/v2/api-docs"))
//                .withConfig(config)
//                .build()
//                .toFile(Paths.get("src/docs/asciidoc/generated/api"));
//    }
//    @Test
//    public void generatePDF() {
//        //样式
//        String style = "pdf-style=E:\\themes\\theme.yml";
//        //字体
//        String fontsdir = "pdf-fontsdir=E:\\fonts";
//        //需要指定adoc文件位置
//        String adocPath = "E:\\all.adoc";
//        AsciidoctorInvoker.main(new String[]{"-a",style,"-a",fontsdir,"-b","pdf",adocPath});
//    }
//}
