package pu.web.cheminfo;

import java.util.ArrayList;
import java.util.List;

import org.openscience.cdk.interfaces.IAtomContainer;

public class ChemInfoServer 
{
	public static ChemInfoServer defaultServer = new ChemInfoServer();
	
	//Config data
	public int maxNumberOfMolecules = 200;

	//Work data
	public List<IAtomContainer> molecules = new ArrayList<IAtomContainer>();
	public int nextID = 1;
	
	public void addMolecule(IAtomContainer mol) {
		if (molecules.size() >= maxNumberOfMolecules)
			molecules.remove(0);
		molecules.add(mol);
		mol.setProperty("M_ID", "" + nextID); //id is stored as string
		nextID++;
	}
	
	public IAtomContainer getMoleculeById(String id) {
		for (IAtomContainer mol : molecules) {
			if (mol.getProperty("M_ID").equals(id))
				return mol;
		}
			
		return null;
	}

}
