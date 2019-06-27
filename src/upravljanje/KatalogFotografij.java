package upravljanje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Fotografija;
import model.Oseba;
import model.Oznaka;

public class KatalogFotografij {
	
	private Map<Integer, Oseba> osebaMap = new HashMap<>();
	
	private Map<Integer, Oznaka> oznakaMap = new HashMap<>();
	
	private Map<Integer, Fotografija> fotografijaMap = new HashMap<>();
	
	public static void main(String[] args) {
		KatalogFotografij katalog = new KatalogFotografij();
		
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			zanka:
			for (;;) {
				System.out.print("foto> ");	
				String command = reader.readLine().toLowerCase();
							
				int osebaId;
				int oznakaId;
				int fotoId;
				
				switch (command) {
				case "izhod":
					break zanka;
					
				case "dodaj osebo":
					
					System.out.print("id: ");	
					osebaId = readID(reader.readLine());
					if(osebaId==-1) break;
					System.out.print("ime: ");	
					String ime = reader.readLine();
					System.out.print("priimek: ");	
					String priimek = reader.readLine();
					
					katalog.dodajOsebo(osebaId, ime, priimek);
					break;
					
				case "izpisi osebe":
					katalog.izpisiOsebe();					
					break;
					
				case "dodaj oznako":

					System.out.print("id: ");
					oznakaId = readID(reader.readLine());
					if(oznakaId==-1) break;
					System.out.print("opis: ");	
					String opis = reader.readLine();
					
					katalog.dodajOznako(oznakaId, opis);
					break;
					
				case "izpisi oznake":
					katalog.izpisiOznake();					
					break;
					
				case "dodaj foto":
					
					System.out.print("id: ");	
					fotoId = readID(reader.readLine());
					if(fotoId==-1) break;
					System.out.print("opis: ");	
					String fotoOpis = reader.readLine();
					System.out.print("sirina: ");	
					Integer fotoSirina = Integer.parseInt(reader.readLine());
					System.out.print("visina: ");	
					Integer fotoVisina = Integer.parseInt(reader.readLine());
					
					katalog.dodajFotografijo(
							fotoId, fotoSirina, fotoVisina, fotoOpis, 
							new ArrayList<>(), new ArrayList<>());
					break;
					
				case "dodaj osebo na foto":
					
					System.out.print("id fotografije: ");	
					fotoId = readID(reader.readLine());
					if(fotoId==-1) break;
					System.out.print("id osebe: ");
					osebaId = readID(reader.readLine());
					if(osebaId==-1) break;
					
					katalog.dodajOseboNaFotografijo(fotoId, osebaId);
					break;
					
				case "dodaj oznako na foto":
					
					System.out.print("id fotografije: ");	
					fotoId = readID(reader.readLine());
					if(fotoId==-1) break;
					System.out.print("id oznake: ");
					oznakaId = readID(reader.readLine());
					if(oznakaId==-1) break;
					
					katalog.dodajOznakoNaFotografijo(fotoId, oznakaId);
					break;
					
				case "izpisi foto":
					katalog.izpisiFotografije();					
					break;
					
				default:
					 System.out.println(Commando.getCommands());
					 break;
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void dodajOsebo(Integer id, String ime, String priimek) {
		Oseba o = new Oseba();
		o.setId(id);
		o.setIme(ime);
		o.setPriimek(priimek);
		
		this.osebaMap.put(o.getId(), o);
	}
	
	public void dodajOznako(Integer id, String opis) {
		Oznaka o = new Oznaka();
		o.setId(id);
		o.setOpis(opis);
		
		this.oznakaMap.put(o.getId(), o);
	}
	
	public void dodajFotografijo(
			Integer id, Integer sirina, Integer visina, String opis, 
			List<Oseba> osebaList, List<Oznaka> oznakaList) {
		Fotografija f = new Fotografija();
		f.setId(id);
		f.setSirina(sirina);
		f.setVisina(visina);
		f.setOpis(opis);
		f.setOsebaList(osebaList);
		f.setOznakaList(oznakaList);
		
		this.fotografijaMap.put(f.getId(), f);
	}
	
	public void dodajOseboNaFotografijo(
			Integer fotoId, Integer osebaId) {
		
		Fotografija f = fotografijaMap.get(fotoId);
		Oseba o = osebaMap.get(osebaId);
		
		f.getOsebaList().add(o);
	}
	
	public void dodajOznakoNaFotografijo(
			Integer fotoId, Integer oznakaId) {
		
		Fotografija f = fotografijaMap.get(fotoId);
		Oznaka o = oznakaMap.get(oznakaId);
		
		f.getOznakaList().add(o);
	}
	
	public void izpisiOsebe() {
		System.out.println("OSEBE:");
		for (Oseba o : this.getOsebaMap().values()) {
			System.out.println(o);
		}
		System.out.println("-----");
	}
	
	public void izpisiOznake() {
		System.out.println("OZNAKE:");
		for (Oznaka o : this.getOznakaMap().values()) {
			System.out.println(o);
		}
		System.out.println("-----");
	}
	
	public void izpisiFotografije() {
		System.out.println("FOTOGRAFIJE:");
		for (Fotografija f : this.getFotografijaMap().values()) {
			System.out.println(f);
		}
		System.out.println("-----");
	}
	
	public Map<Integer, Oseba> getOsebaMap() {
		return osebaMap;
	}

	public Map<Integer, Oznaka> getOznakaMap() {
		return oznakaMap;
	}

	public Map<Integer, Fotografija> getFotografijaMap() {
		return fotografijaMap;
	}

	static int readID(String id){
		try{
			return Integer.parseInt(id);
		} catch (java.lang.NumberFormatException e){
			System.out.println("Napačen vnos! Prosimo vnesite število.");
			return -1;
		}
	}

}
