
package logica.servidor;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the logica.servidor package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ExcepcionKeywordVacia_QNAME = new QName("http://servidor.logica/", "ExcepcionKeywordVacia");
    private final static QName _ExceptionUsuarioSeSigueASiMismo_QNAME = new QName("http://servidor.logica/", "ExceptionUsuarioSeSigueASiMismo");
    private final static QName _ExceptionValidezNegativa_QNAME = new QName("http://servidor.logica/", "ExceptionValidezNegativa");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: logica.servidor
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExcepcionKeywordVacia }
     * 
     * @return
     *     the new instance of {@link ExcepcionKeywordVacia }
     */
    public ExcepcionKeywordVacia createExcepcionKeywordVacia() {
        return new ExcepcionKeywordVacia();
    }

    /**
     * Create an instance of {@link ExceptionUsuarioSeSigueASiMismo }
     * 
     * @return
     *     the new instance of {@link ExceptionUsuarioSeSigueASiMismo }
     */
    public ExceptionUsuarioSeSigueASiMismo createExceptionUsuarioSeSigueASiMismo() {
        return new ExceptionUsuarioSeSigueASiMismo();
    }

    /**
     * Create an instance of {@link ExceptionValidezNegativa }
     * 
     * @return
     *     the new instance of {@link ExceptionValidezNegativa }
     */
    public ExceptionValidezNegativa createExceptionValidezNegativa() {
        return new ExceptionValidezNegativa();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     * @return
     *     the new instance of {@link DtUsuario }
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link ArrayList }
     * 
     * @return
     *     the new instance of {@link ArrayList }
     */
    public ArrayList createArrayList() {
        return new ArrayList();
    }

    /**
     * Create an instance of {@link DtPostulante }
     * 
     * @return
     *     the new instance of {@link DtPostulante }
     */
    public DtPostulante createDtPostulante() {
        return new DtPostulante();
    }

    /**
     * Create an instance of {@link LocalDate }
     * 
     * @return
     *     the new instance of {@link LocalDate }
     */
    public LocalDate createLocalDate() {
        return new LocalDate();
    }

    /**
     * Create an instance of {@link DtEmpresa }
     * 
     * @return
     *     the new instance of {@link DtEmpresa }
     */
    public DtEmpresa createDtEmpresa() {
        return new DtEmpresa();
    }

    /**
     * Create an instance of {@link WrapperLista }
     * 
     * @return
     *     the new instance of {@link WrapperLista }
     */
    public WrapperLista createWrapperLista() {
        return new WrapperLista();
    }

    /**
     * Create an instance of {@link DtOfertaExtendido }
     * 
     * @return
     *     the new instance of {@link DtOfertaExtendido }
     */
    public DtOfertaExtendido createDtOfertaExtendido() {
        return new DtOfertaExtendido();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExcepcionKeywordVacia }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExcepcionKeywordVacia }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor.logica/", name = "ExcepcionKeywordVacia")
    public JAXBElement<ExcepcionKeywordVacia> createExcepcionKeywordVacia(ExcepcionKeywordVacia value) {
        return new JAXBElement<>(_ExcepcionKeywordVacia_QNAME, ExcepcionKeywordVacia.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExceptionUsuarioSeSigueASiMismo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExceptionUsuarioSeSigueASiMismo }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor.logica/", name = "ExceptionUsuarioSeSigueASiMismo")
    public JAXBElement<ExceptionUsuarioSeSigueASiMismo> createExceptionUsuarioSeSigueASiMismo(ExceptionUsuarioSeSigueASiMismo value) {
        return new JAXBElement<>(_ExceptionUsuarioSeSigueASiMismo_QNAME, ExceptionUsuarioSeSigueASiMismo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExceptionValidezNegativa }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExceptionValidezNegativa }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor.logica/", name = "ExceptionValidezNegativa")
    public JAXBElement<ExceptionValidezNegativa> createExceptionValidezNegativa(ExceptionValidezNegativa value) {
        return new JAXBElement<>(_ExceptionValidezNegativa_QNAME, ExceptionValidezNegativa.class, null, value);
    }

}
