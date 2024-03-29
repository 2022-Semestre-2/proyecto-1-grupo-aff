package com.mycompany.proyecto1alospits;
import javax.swing.*;
import java.io.*;

/**
 * Clase donde se registran las marcas de vehiculos automoviles
 * @author Luis Barreda, Fabian Miranda, Franco Rojas
 * @see Marca
 */
public class Marca {

    File file = new File("marcas.csv");
    File temp_file = new File("temp.csv");
    String lineas_archivo;
    String[] marca_tipo_array;
    FileWriter fw;
    BufferedWriter bw;
    PrintWriter pw;
    BufferedReader lector;

    /**
     * Método que agrega una nueva marca al csv
     * @param marca marca a agregar
     * @param tipo tipo de vehículo a agregar
     * @throws IOException
     */

    public void add(String marca, String tipo) throws IOException {
        boolean ismarca = true;
        try{
            lector = new BufferedReader(new FileReader(file));

            while ((lineas_archivo = lector.readLine()) != null){
                String[] fila = lineas_archivo.split(",");
                if (fila[0].equals(marca) && fila[1].equals(tipo)) {
                    ismarca = false;
                    break;
                }
            }
        }
        catch(Exception e) {e.printStackTrace();}
        finally {lector.close();}

        if (ismarca){
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.println(marca + "," + tipo);
            pw.flush();
            pw.close();
            JOptionPane.showMessageDialog(null, marca + "," + tipo + " Añadida", "", 1);
        }
        else {
            JOptionPane.showMessageDialog(null, marca + "," + tipo + " previamente añadida", "", 1);
        }
    }

    /**
     * Método que elimina una marca del csv
     * @param marca marca a eliminar
     * @param tipo tipo de vehículo a eliminar
     * @throws IOException
     */

    public void delete(String marca, String tipo) throws IOException {
        try{
            fw = new FileWriter(temp_file, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            boolean isdeleted = false;

            lector = new BufferedReader(new FileReader(file));
            while ((lineas_archivo = lector.readLine()) != null){
                String[] columnas = lineas_archivo.split(",");
                if (!columnas[0].equals(marca) || !columnas[1].equals(tipo)) {
                    pw.println(lineas_archivo);
                }
                else {
                    isdeleted = true;
                }
            }

            pw.flush();
            pw.close();

            FileInputStream in = new FileInputStream(temp_file);
            FileOutputStream out = new FileOutputStream(file);
            try{
                int n;

                while ((n = in.read()) != -1){
                    out.write(n);
                }
            }finally{
                if (in != null){
                    in.close();
                }
                if (out != null){
                    out.close();
                }
            }
            temp_file.delete();

            if (isdeleted == true){
                JOptionPane.showMessageDialog(null, marca + "," + tipo + " Eliminada", "", 1);
            }
            else{
                JOptionPane.showMessageDialog(null, marca + "," + tipo + " previamente eliminada", "", 1);
            }
        }
        catch(Exception e) {e.printStackTrace();}
        finally {lector.close();}
    }

    /**
     * Método que retorna las marcas con su respectivo tipo en forma de arreglo
     * @return retorna un arreglo con las marcas y tipos
     */

    public String[] getMarcaTipo() {
        int cont = 0, csv_len = getCSVLen();
        boolean not_titulo = false;
        marca_tipo_array = new String[csv_len - 1];
        try {
            lector = new BufferedReader(new FileReader(file));
            while ((lineas_archivo = lector.readLine()) != null) {
                if (not_titulo) {
                    marca_tipo_array[cont] = lineas_archivo;
                    cont++;
                } else {
                    not_titulo = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sort_string_array();
        return marca_tipo_array;
    }

    /**
     * Método que ordena el arreglo de marcas en orden alfabético
     */
    public void sort_string_array(){
        int size = marca_tipo_array.length;

        for(int i = 0; i<size-1; i++) {
            for (int j = i+1; j<marca_tipo_array.length; j++) {
                if(marca_tipo_array[i].compareTo(marca_tipo_array[j])>0) {
                    String temp = marca_tipo_array[i];
                    marca_tipo_array[i] = marca_tipo_array[j];
                    marca_tipo_array[j] = temp;
                }
            }
        }
    }

    /**
     * Metodo que calcula la cantidad de filas que existen en el csv
     * @return entero con la cantidad de filas
     */
    public int getCSVLen(){
        int csv_len = 0;
        try{
            lector = new BufferedReader(new FileReader(file));
            while((lineas_archivo = lector.readLine()) != null){
                csv_len++;
            }
        }catch(Exception e) {e.printStackTrace();}
        return csv_len;
    }
}
