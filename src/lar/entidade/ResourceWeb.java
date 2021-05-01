/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lar.entidade;

import java.awt.Image;
import javax.swing.DefaultListModel;
import org.apache.jena.rdf.model.Resource;

/**
 *
 * @author Renato
 */
public class ResourceWeb {

    private String id;
    private String label;
    private String comment;
    private String uri;
    private String dataset;
    private String wikidatacode;
    private String image;
    private DefaultListModel drugs;
    private DefaultListModel alsoKnowAs;
    private DefaultListModel activeIngredientIn;

    @Override
    public String toString() {
        return getLabel();
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return the dataset
     */
    public String getDataset() {
        return dataset;
    }

    /**
     * @param dataset the dataset to set
     */
    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    /**
     * @return the wikidatacode
     */
    public String getWikidatacode() {
        return wikidatacode;
    }

    /**
     * @param wikidatacode the wikidatacode to set
     */
    public void setWikidatacode(String wikidatacode) {
        this.wikidatacode = wikidatacode;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the drugs
     */
    public DefaultListModel getDrugs() {
        return drugs;
    }

    /**
     * @param drugs the drugs to set
     */
    public void setDrugs(DefaultListModel drugs) {
        this.drugs = drugs;
    }

    /**
     * @return the alsoKnowAs
     */
    public DefaultListModel getAlsoKnowAs() {
        return alsoKnowAs;
    }

    /**
     * @param alsoKnowAs the alsoKnowAs to set
     */
    public void setAlsoKnowAs(DefaultListModel alsoKnowAs) {
        this.alsoKnowAs = alsoKnowAs;
    }

    /**
     * @return the activeIngredientIn
     */
    public DefaultListModel getActiveIngredientIn() {
        return activeIngredientIn;
    }

    /**
     * @param activeIngredientIn the activeIngredientIn to set
     */
    public void setActiveIngredientIn(DefaultListModel activeIngredientIn) {
        this.activeIngredientIn = activeIngredientIn;
    }
}
