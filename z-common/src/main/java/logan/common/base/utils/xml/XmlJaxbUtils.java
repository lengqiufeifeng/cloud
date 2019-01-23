package logan.common.base.utils.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

@SuppressWarnings("restriction" )

public class XmlJaxbUtils {
    @SuppressWarnings("unchecked" )
    public static <E> E fromXML(String xml, Class<E> clazz) throws Exception {
        JAXBContext context = JAXBContext.newInstance(new Class[]{clazz});
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try {
            return (E) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            throw new Exception("解析xml出错! [" + xml + "]", e);
        }
    }


    @SuppressWarnings("unchecked" )
    public static <E> E fromXML(File xmlFile, Class<E> clazz) throws Exception {
        JAXBContext context = JAXBContext.newInstance(new Class[]{clazz});
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try {
            return (E) unmarshaller.unmarshal(xmlFile);
        } catch (Exception e) {
            throw new Exception("解析xml出错! ! " + xmlFile, e);
        }
    }

    public static String toXML(Object pojo) throws Exception {
        return toXML(pojo, "UTF-8" );
    }

    public static String toXML(Object pojo, String encoding) throws Exception {
        JAXBContext context = JAXBContext.newInstance(new Class[]{pojo.getClass()});
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty("jaxb.encoding", encoding);
        marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
        StringWriter writer = new StringWriter();
        marshaller.marshal(pojo, writer);
        String xml = writer.toString();
        return xml;
    }

    public static void toXML(Object pojo, File xmlFile) throws Exception {
        toXML(pojo, "UTF-8", xmlFile);
    }

    public static void toXML(Object pojo, String encoding, File xmlFile) throws Exception {
        JAXBContext context = JAXBContext.newInstance(new Class[]{pojo.getClass()});
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty("jaxb.encoding", encoding);
        marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
        marshaller.marshal(pojo, xmlFile);
    }
}
