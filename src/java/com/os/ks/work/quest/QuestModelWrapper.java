/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.quest;

import com.os.ks.work.quest.*;
import com.os.ks.work.quest.*;
import com.os.models.Category;
import com.os.models.Quest;
import com.os.util.hjpf.wrapper.AbstractModelWrapper;

/**
 *
 * @author egyptianeagle
 */
public class QuestModelWrapper extends AbstractModelWrapper<Quest> {

    public QuestModelWrapper() {
        initModel();
    }
    private Integer id;
    private String questName;
    private Integer questType;
    private String categoryTitle;

    public QuestModelWrapper(Integer id, String questName) {
        this.id = id;
        this.questName = questName;
    }

    public QuestModelWrapper(Integer id, String questName, Integer questType) {
        this.id = id;
        this.questName = questName;
        this.questType = questType;
    }

    public QuestModelWrapper(Integer id, String questName, Integer questType, String categoryTitle) {
        this.id = id;
        this.questName = questName;
        this.questType = questType;
        this.categoryTitle = categoryTitle;
    }

    public QuestModelWrapper(Integer id, String questName, String categoryTitle) {
        this.id = id;
        this.questName = questName;
        this.categoryTitle = categoryTitle;
    }

    @Override
    public Quest getModel() {
        return model;
    }

    @Override
    public Integer getId() {
        return id;
    }

    private void initModel() {
        Quest quest = new Quest();
        quest.setCategory(new Category());
        setModel(quest);
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public Integer getQuestType() {
        return questType;
    }

    public void setQuestType(Integer questType) {
        this.questType = questType;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

}
