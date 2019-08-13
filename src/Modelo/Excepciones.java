/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * Ésta clase permite crear un objeto de tipo Excepciones partiendo de las
 * necesidades que tengan sus dependencias de validar sus los datos de entrada,
 * reaccionando con una excepción o mensaje de salida.
 *
 * Se usa la siguiente sintáxis: objeto = new Excepciones();
 *
 * @author Kevin
 * @version 1.0
 */
public class Excepciones {

    /**
     *
     * Constructor vacio que instancia un objeto de tipo Excepciones con el
     * objetivo de acceder a sus métodos.
     *
     */
    public Excepciones() {
    }

    /**
     * Ésta clase se clasifica en 3 secciones, la primera corresponde a las
     * validaciones del Formulario principal, seguido de validaciones Generales
     * y finalmente la validaciones de los registros en tabla.
     */
    /**
     * Como parte de las validaciones del Formulario principal, éste método no
     * devuelve ningún tipo de valor, pero verifica si existe una ventana
     * abierta en el conjunto de JInternalFrames del formulario principal.
     *
     * @param inter JInternalFrame
     * @param Principal JDesktopPane
     */
    public void controlaInstancia(JInternalFrame inter, JDesktopPane Principal) {
        boolean mostrar = true;
        String Nombre = inter.getTitle();
        for (int a = 0; a < Principal.getComponentCount(); a++) {     // verificar si es instancia de algun componente que ya este en el jdesktoppane
            if (inter.getClass().isInstance(Principal.getComponent(a))) {
                JOptionPane.showMessageDialog(null, "La ventana '" + Nombre + "' se encuentra activa\n\nCierrela para acceder de nuevo a su contenido");
                inter.toFront();
                Principal.moveToFront(inter);
                mostrar = false;
            } else {
                inter.setVisible(false);
            }
        }
        if (mostrar) {
            Principal.add(inter);
        }
        inter.show();
    }

    //Validaciones Generales.
    /**
     *
     * Validación General: Devuelve cualquier número de formato de entrada
     * double a un formato Cadena definido por 3 digitos y una coma (permitiendo
     * como máximo 2 decimales).
     *
     * @param numero double
     * @return String
     */
    public String formateaDouble(double numero) {
        BigDecimal trans = new BigDecimal(numero);
        DecimalFormat formatea = new DecimalFormat("###,###.##");
        return formatea.format(trans);
    }

    /**
     *
     * Validación General: Devuelve una respuesta en la existencia de campos
     * vacios de un formulario en formato boolean.
     *
     * @param txtN JTextField
     * @param txtA JTextField
     * @param txtId JTextField
     * @param txtT JTextField
     * @param txtP JTextField
     * @param txtM JTextField
     * @return boolean
     */
    public boolean validarContenido(JTextField txtN, JTextField txtA, JTextField txtId, JTextField txtT, JTextField txtP, JTextField txtM) {
        return (!txtN.getText().trim().equals("")
                && !txtA.getText().trim().equals("")
                && !txtId.getText().trim().equals("")
                && !txtT.getText().trim().equals("")
                && !txtP.getText().trim().equals("")
                && !txtM.getText().trim().equals(""));
    }

    /**
     *
     * Validación General: Devuelve una respuesta en la existencia de campos
     * vacios erroneos de un formulario (demarcados por un color rojo) en
     * formato boolean.
     *
     * @param txtN JTextField
     * @param txtA JTextField
     * @param txtId JTextField
     * @param txtT JTextField
     * @param txtP JTextField
     * @param txtM JTextField
     * @return boolean
     */
    public boolean validarCamposErr(JTextField txtN, JTextField txtA, JTextField txtId, JTextField txtT, JTextField txtP, JTextField txtM) {
        return (!txtN.getForeground().equals(Color.red)
                && !txtA.getForeground().equals(Color.red)
                && !txtId.getForeground().equals(Color.red)
                && !txtT.getForeground().equals(Color.red)
                && !txtP.getForeground().equals(Color.red)
                && !txtM.getForeground().equals(Color.red));
    }

    /**
     *
     * Validación General: Devuelve una respuesta de la existencia de un
     * elemento digitado por el usuario que posiblemente este contenido en
     * JComboBox, ésta respuesta es devuelta en formato boolean.
     *
     * @param cilindraje String
     * @param cmb JComboBox
     * @return boolean
     */
    public boolean validarExistCmb(String cilindraje, JComboBox cmb) {
        boolean estado = false;
        for (int i = 0; i < cmb.getItemCount(); i++) {
            estado = cmb.getItemAt(i).equals(cilindraje);
            if (estado == true) {
                break;
            }
        }
        return estado;
    }

    /**
     *
     * Validación General: Verifica que en el evento de tipo KeyEvent de la caja
     * de texto se conviertan los caracteres de tipo Letter a mayúsculas.
     *
     * @param evt java.awt.event.KeyEvent
     */
    public void validarMayuscula(java.awt.event.KeyEvent evt) {
        Character c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.setKeyChar(Character.toUpperCase(c));
        }
    }

    /**
     *
     * Validación General: Verifica que una expresión regular este correctamente
     * formulada.
     *
     * @param txt JTextField
     * @param Expresion String
     */
    public void validarExpresion(JTextField txt, String Expresion) {
        Pattern pat = Pattern.compile(Expresion);
        Matcher mat = pat.matcher(txt.getText());
        if (mat.matches()) {
            txt.setForeground(Color.black);
        } else {
            txt.setForeground(Color.red);
        }
    }

    /**
     *
     * Validación General: Convierte la primera letra de cada palabra en una
     * letra Mayúscula.
     *
     * @param txt JTextField
     * @param texto String
     */
    public void letraCapital(JTextField txt, String texto) {
        char[] caracteres = texto.toCharArray();
        try {
            caracteres[0] = Character.toUpperCase(caracteres[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ha usado un espacio y se ha generado la excepción: " + e.toString());
        }
        for (int i = 0; i < texto.length() - 2; i++) // Es 'palabra'
        {
            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',') // Reemplazamos
            {
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
            }
        }
        txt.setText(new String(caracteres));
    }

    /**
     *
     * Validación General: Verifica que la placa digitada no se encuentre en el
     * ArrayList retornando una respuesta en formato boolean.
     *
     * @param auxVehiculo
     * @param Placa String
     * @param NF
     * @param Col
     * @return boolean
     * @throws java.io.IOException
     */
    
    /*
    public boolean validarRegistroPlaca(CompilacionVehiculos auxVehiculo, String Placa, int NF, int Col) throws IOException {
        boolean resp = false;
        for (int i = NF; i < auxVehiculo.getConArch().leerDatos().split(";").length; i += Col) {
            if (auxVehiculo.getConArch().leerDatos().split(";")[i].equalsIgnoreCase(Placa)) {
                resp = true;
                break;
            }
        }
        return resp;
    }*/

    /**
     *
     * Validación General: Verifica que la Identificación del propietario del
     * automóvil no se encuentre dentro del ArrayList retornando una respuesta
     * en formato boolean.
     *
     * @param lista ArrayList
     * @param Id String
     * @return boolean
     */

    /*public boolean validarRegistroId(ArrayList<Vehiculo> lista, String Id) {
        boolean resp = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPropietario().get(0).getIdentificacion().equals(Id)) {
                resp = true;
                break;
            }
        }
        return resp;
    }*/

    /**
     *
     * Validación General: Verifica que lo que se ha digitado sea realmente una
     * letra.
     *
     * @param evt java.awt.event.KeyEvent
     */
    public void validarLetra(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            toolkit.beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresa solo letras", "Mensaje de error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *
     * Validación General: Verifica que la entrada de texto sea un conjunto de
     * solo caracteres númericos.
     *
     * @param evt java.awt.event.KeyEvent
     */
    public void validarNum(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            toolkit.beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresa solo números", "Mensaje de error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *
     * Validación General: Verifica que lo que contenga una cadena de caracteres
     * de texto contenga una respuesta en formato boolean un conjunto númerico.
     *
     * @param elemento String
     * @return boolean
     */
    public boolean validarNum(String elemento) {
        boolean resp = false;
        char[] c = elemento.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (Character.isLetter(c[i])) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                toolkit.beep();
                resp = true;
                break;
            } else {
                resp = false;
            }
        }

        return resp;
    }

    /**
     *
     * Validación General: Verifica que al seleccionar la opción 'Otro' en un
     * JComboBox se despligue la opción de digitar el número.
     *
     * @param cmb JComboBox
     */
    public void seleccionCilindraje(JComboBox cmb) {
        String nCilindraje;
        if (cmb.getSelectedItem().equals("Otro")) {
            nCilindraje = validarN(JOptionPane.showInputDialog("Digite el nuevo cilindraje"));

            if (validarExistCmb(nCilindraje, cmb)) {
                int resp = JOptionPane.showConfirmDialog(null, "Ya se encuentra registrado\n\n¿Desea seleccionarlo?", "Mensaje de error", JOptionPane.ERROR_MESSAGE);
                while (resp != JOptionPane.OK_OPTION) {
                    nCilindraje = validarN(JOptionPane.showInputDialog("Digite el nuevo cilindraje"));
                    resp = JOptionPane.showConfirmDialog(null, "¿Desea registrarlo?", "Mensaje de error", JOptionPane.ERROR_MESSAGE);
                }
                if (resp == JOptionPane.OK_OPTION) {
                    cmb.addItem(nCilindraje);
                    cmb.setSelectedItem(nCilindraje);
                } else {
                    cmb.setSelectedItem(nCilindraje);
                }
            } else {
                cmb.addItem(nCilindraje);
                cmb.setSelectedItem(nCilindraje);
            }
        }
    }

    /**
     *
     * Validación General: Verifica mediente un retorno recursivo que una
     * entrada de texto sea un numero o no sea valida dentro del contexto,
     * devolviendo un mensaje en formato cadena.
     *
     * @param num String
     * @return String
     */
    public String validarN(String num) {
        try {
            while (validarNum(num) || Integer.parseInt(num) < 0) {
                JOptionPane.showMessageDialog(null, num + ", no es un número valido, debido a que contiene letras.", "Mensaje de error", JOptionPane.ERROR_MESSAGE);
                num = JOptionPane.showInputDialog("Digite el nuevo cilindraje");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No puede dejar el campo vacio", "Mensaje de error", JOptionPane.ERROR_MESSAGE);
            num = validarN(JOptionPane.showInputDialog("Digite el nuevo cilindraje"));
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "No puede salir sin antes completar el campo", "Mensaje de error", JOptionPane.ERROR_MESSAGE);
            num = validarN(JOptionPane.showInputDialog("Digite el nuevo cilindraje"));
        }
        return num;
    }
    
    public boolean validarEmail(String email) {
        String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Validaciones de los registros en tabla
    /**
     *
     * Validaciones de los registros en tabla: Remueve los elementos contenidos
     * en un DefaultTableModel.
     *
     * @param tbl JTable
     * @param plantilla DefaultTableModel
     */
    public void limpiarTabla(JTable tbl, DefaultTableModel plantilla) {
        for (int i = 0; i < tbl.getRowCount(); i++) {
            plantilla.removeRow(i);
            i -= 1;
        }
    }

    /**
     *
     * Validaciones de los registros en tabla: Retorna un arreglo de objetos
     * compuesto por los diferentes datos establecidos en el Modelo del JTable.
     *
     * @param Tipo String
     * @param i Integer
     * @param auxVehiculo ArrayList
     * @return Object[]
     */

    /*
    public Object[] crearObjeto(String Tipo, int i, ArrayList<Vehiculo> auxVehiculo) {
        Object objeto[] = {Tipo,
            auxVehiculo.get(i).getPropietario().get(0).getNombre() + " " + auxVehiculo.get(i).getPropietario().get(0).getApellido(),
            auxVehiculo.get(i).getPlaca(),
            formateaDouble(auxVehiculo.get(i).getValor()),
            formateaDouble(auxVehiculo.get(i).Impuesto())};
        return objeto;
    }
     */
    /**
     *
     * Validaciones de los registros en tabla: Asigna un objeto de arreglos
     * definido a partir de una instancia de una clase (Auto, Moto o
     * Servicio_Publico).
     *
     * @param auxVehiculo CompilacionVehiculos
     * @param plantilla DefaultTableModel
     * @return int
     */
    
    /*public int asignarFilas(CompilacionVehiculos auxVehiculo, DefaultTableModel plantilla) {
        String[] Reg = auxVehiculo.toStringArch();
        int NumC = 0;
        for (int i = 0; i < auxVehiculo.toStringArch().length; i++) {
            NumC = Reg[i].split(";").length;
            plantilla.addRow(Reg[i].split(";"));
        }
        return NumC;
    }*/
}
