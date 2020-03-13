package org.demos.hello.jackson.snippets.bldng;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JacksonXmlSerializationTest {

    @Test
    public void should_serialize_simplePojo_toXml() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(new SimpleBean());
        System.out.println(xml);
        assertThat(xml).isNotNull();
    }

    @Test
    public void should_serialize_simplePojo_toXml_2() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();

        File file = new File("simple_bean.xml");
        xmlMapper.writeValue(file, new SimpleBean());
        assertThat(file).hasContent("<SimpleBean><x>1</x><y>1</y></SimpleBean>");
    }

    @Test
    public void should_deserialize_xml_to_simplePojo() throws JsonMappingException, JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        SimpleBean deserialized =
                xmlMapper.readValue("<SimpleBean><x>20</x><y>15</y></SimpleBean>", SimpleBean.class);
        assertThat(deserialized.getX()).isEqualTo(20);
        assertThat(deserialized.getY()).isEqualTo(15);
    }

}