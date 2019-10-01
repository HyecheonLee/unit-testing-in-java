package com.hyecheon.iloveyouboss.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Question implements Serializable, Persistable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Integer id;

    @Column
    private String text;
    @Column
    private Instant instant;

    abstract public List<String> getAnswerChoices();

    abstract public boolean match(int expected, int actual);


    public boolean match(Answer answer) {
        return false;
    }

    public String getAnswerChoice(int i) {
        return getAnswerChoices().get(i);
    }

    public int indexOf(String matchingAnswerChoice) {
        for (int i = 0; i < getAnswerChoices().size(); i++) {
            if (getAnswerChoice(i).equals(matchingAnswerChoice))
                return i;
        }
        return -1;
    }

    @Override
    public Instant getCreateTimestamp() {
        return instant;
    }

    @Override
    public void setCreateTimestamp(Instant instant) {
        this.instant = instant;
    }
}
