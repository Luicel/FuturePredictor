package me.calebclifton;

import me.calebclifton.models.Prediction;

import javax.swing.*;
import java.awt.*;

public class FormBuilder {
    private final Container container;
    private GridBagConstraints cons;

    private FormBuilder(Container container) {
        this.container = container;
        container.setLayout(new GridBagLayout());
        initLabelCons();
    }

    public static FormBuilder init(Container container) {
        FormBuilder builder = new FormBuilder(container);
        return builder;
    }

    private void initLabelCons() {
        cons = new GridBagConstraints();
        cons.weightx = 0;
        cons.gridwidth = 1;
        cons.ipadx = 1;
        cons.anchor = GridBagConstraints.CENTER;
        cons.insets = new Insets(3, 5, 3, 5);
    }

    public FormBuilder add(Component component, int x, int y, int width) {
        cons.gridx = x;
        cons.gridy = y;
        cons.gridwidth = width;

        container.add(component, cons);

        return this;
    }

    public FormBuilder addBreak(int y) {
        return add(new JLabel(" "),1, y, 1);
    }

    public FormBuilder setAnchor(int anchor) {
        cons.anchor = anchor;
        return this;
    }

    public static JPanel getBodyPanel(String name) {
        Prediction prediction = new Prediction(name);

        // Sliders
        JSlider overallHappinessSlider = new JSlider(0, 100, prediction.getOverallHappiness());
        overallHappinessSlider.setEnabled(false);
        JSlider findingSuccessSlider = new JSlider(0, 100, prediction.getSuccessChance());
        findingSuccessSlider.setEnabled(false);
        JSlider stubbingToeSlider = new JSlider(0, 100, prediction.getStubbingToeChance());
        stubbingToeSlider.setEnabled(false);
        JSlider rawGroundBeefSlider = new JSlider(0, 100, prediction.getEatingRawGroundBeefChance());
        rawGroundBeefSlider.setEnabled(false);
        JSlider reachDreamsSlider = new JSlider(0, 100, prediction.getReachDreamsChance());
        reachDreamsSlider.setEnabled(false);

        // Labels
        JLabel causeOfDeathLabel = new JLabel(prediction.getCauseOfDeath());
        JLabel favoriteCondimentLabel = new JLabel(prediction.getFavoriteCondiment());

        // CheckBoxes
        JCheckBox trueLoveCheckBox = new JCheckBox();
        trueLoveCheckBox.setSelected(prediction.willFindTrueLove());
        trueLoveCheckBox.setEnabled(false);
        JCheckBox believeThisPredictionCheckBox = new JCheckBox();
        believeThisPredictionCheckBox.setSelected(prediction.willBelievePrediction());
        believeThisPredictionCheckBox.setEnabled(false);

        // Creating Panel
        JPanel panel = new JPanel();
        FormBuilder.init(panel)
                .add(new JLabel("Prediction for \"" + name + "\":"), 1, 1, 5)
                .addBreak(2)
                .setAnchor(GridBagConstraints.WEST)
                .add(new JLabel("Overall Happiness:"), 1, 3, 2)
                .add(overallHappinessSlider, 4, 3, 2)
                .addBreak(4)
                .add(new JLabel("Likeliness of Finding Success:"), 1, 5, 2)
                .add(new JLabel("Likeliness of Stubbing Toe in the Next 72 Hours:"), 1, 6, 2)
                .add(new JLabel("Likeliness of Ever Eating Raw Ground Beef:"), 1, 7, 2)
                .add(new JLabel("Likeliness of Reaching Your Dreams:"), 1, 8, 2)
                .add(new JLabel("Cause of Death:"), 1, 9, 2)
                .add(new JLabel("Favorite Condiment 5 Years From Now:"), 1, 10, 2)
                .add(new JLabel("Will Find True Love:"), 1, 11, 2)
                .add(new JLabel("Will Believe This Prediction:"), 1, 12, 2)
                .setAnchor(GridBagConstraints.CENTER)
                .add(findingSuccessSlider, 4, 5, 2)
                .add(stubbingToeSlider, 4, 6, 2)
                .add(rawGroundBeefSlider, 4, 7, 2)
                .add(reachDreamsSlider, 4, 8, 2)
                .add(causeOfDeathLabel, 4, 9, 2)
                .add(favoriteCondimentLabel, 4, 10, 2)
                .add(trueLoveCheckBox, 4, 11, 2)
                .add(believeThisPredictionCheckBox, 4, 12, 2);

        return panel;
    }
}
