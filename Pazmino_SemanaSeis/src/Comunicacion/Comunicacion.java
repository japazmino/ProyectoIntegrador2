package Comunicacion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import Memoria.Usuario;

public class Comunicacion extends Thread {

	private DatagramSocket socket;
	private String id;
	private LinkedList<Usuario> usuarios;
	private int puerto;

	public Comunicacion() {
		puerto = 5100;

		usuarios = new LinkedList<Usuario>();

		try {
			socket = new DatagramSocket(puerto);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				recibir();
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void enviar(Mensaje msj) {
		byte[] datos = objectByte(msj);
		try {
			DatagramPacket enviar = new DatagramPacket(datos, datos.length, InetAddress.getByName(id.split("/")[0]), puerto);
			socket.send(enviar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void recibir() {
		byte[] buzon = new byte[1024];
		DatagramPacket pack = new DatagramPacket(buzon, buzon.length);
		try {
			socket.receive(pack);
			id = pack.getAddress().toString();
			Mensaje msj = byteObjeto(pack.getData());
			System.out.println("recibi");
			
			if (msj.getTipoIngreso().equals("Registro")) {
				if (usuarios.size() <= 0) {
					Mensaje mensaje = new Mensaje("existe", false);
					enviar(mensaje);
					usuarios.add(new Usuario(msj.getNombre(), msj.getContra()));
				} else {
					for (int i = 0; i <= usuarios.size(); i++) {
						if (usuarios.get(i).getUsuario().equals(msj.getNombre())) {
							Mensaje mensaje = new Mensaje("existe", true);
							enviar(mensaje);
							System.out.println("entro 1");
						} else {
							Mensaje mensaje = new Mensaje("existe", false);
							enviar(mensaje);
							usuarios.add(new Usuario(msj.getNombre(), msj.getContra()));
							System.out.println("entro 2");
						}
					}
				}
			}
			
			if (msj.getTipoIngreso().equals("Ingreso")) {
				Mensaje mensaje = new Mensaje("existe", true);
				enviar(mensaje);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public byte[] objectByte(Mensaje param) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		try {
			ObjectOutputStream os = new ObjectOutputStream(bytes);
			os.writeObject(param);
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes.toByteArray();
	}

	public Mensaje byteObjeto(byte[] bytes) {
		ByteArrayInputStream byteArray = new ByteArrayInputStream(bytes);
		Mensaje aux = null;
		try {
			ObjectInputStream is = new ObjectInputStream(byteArray);
			aux = (Mensaje) is.readObject();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aux;
	}

}
