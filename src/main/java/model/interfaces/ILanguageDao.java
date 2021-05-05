package model.interfaces;

import java.util.List;

import model.entities.Language;

public interface ILanguageDao {
	
	public void createOrUpdateLanguage(Language language);
	
	public void deleteLanguage(Language language);
	
	public List<Language> listOfLanguage();
	
	public Language findLanguageByName(String name);
}
