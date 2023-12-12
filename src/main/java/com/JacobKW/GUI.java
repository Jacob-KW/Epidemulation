package com.JacobKW;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;
import java.util.*;

public class GUI extends JFrame implements ActionListener {

    int sizeGrid = 75;
    int startInfected = sizeGrid*sizeGrid/5;

    JLabel versionNumber = new JLabel("Developer Version 0.1.0.1");

    // Global population
    Border blacklineThick = BorderFactory.createLineBorder(Color.BLACK, 3);

    // Frame population
    JPanel custom = new JPanel();
    JPanel settings = new JPanel();
    JPanel header = new JPanel();
    JPanel footer = new JPanel();
    JPanel main = new JPanel();
    JTabbedPane cont = new JTabbedPane();
    ImageIcon virusImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Virus.png")));
    ImageIcon personInfected = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PersonRED.png")));
    ImageIcon personNormal = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PersonGREEN.png")));
    ImageIcon personDead = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PersonBLUE.png")));
    ImageIcon rewindImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Rewind.png")));
    ImageIcon plotImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Plot.png")));
    ImageIcon forwardImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Continue.png")));

    // Custom population
    JPanel fontPanel = new JPanel();
    JPanel themePanel = new JPanel();
    JPanel modPanel = new JPanel();
    JPanel boldPanel = new JPanel();
    JPanel saveCustomPanel = new JPanel();
    JPanel placeHoldCustom = new JPanel();
    JPanel placeHold2Custom = new JPanel();

    // Font customisation
    JLabel addFont = new JLabel("Font:");
    String[] fontArray = { "Arial", "Bahnschrift", "Calibri", "Comic Sans MS", "Courier New", "Impact", "Jokerman" };
    JComboBox<String> font = new JComboBox<String>(fontArray);

    // Theme customisation
    JLabel addTheme = new JLabel("Theme:");
    String[] themeArray = { "Light", "Dark", "Contrast" };
    JComboBox<String> theme = new JComboBox<String>(themeArray);

    // Text modifier customisation
    JLabel addMod = new JLabel("Text Size Modifier: ");
    String[] modArray = { "Medium", "Small", "Large" };
    JComboBox<String> modifier = new JComboBox<String>(modArray);

    // Bold customisation
    JLabel addBold = new JLabel("Bold text: ");
    JCheckBox bold = new JCheckBox();

    // Save customisation
    JButton saveCustom = new JButton("SAVE");

    // Settings population
    JPanel namePanel = new JPanel();
    JPanel behaviourPanel = new JPanel();
    JPanel distancingPanel = new JPanel();
    JPanel PPEPanel = new JPanel();
    JPanel popNoPanel = new JPanel();
    JPanel popAreaPanel = new JPanel();
    JPanel sanitationPanel = new JPanel();
    JPanel saveSettingsPanel = new JPanel();
    JPanel placeHoldSettings = new JPanel();
    JPanel placeHold2Settings = new JPanel();

    // Name settings
    JLabel addName = new JLabel("Disease Name: ");
    JTextField name = new JTextField(15);

    // Behaviour settings
    JLabel addBehaviour = new JLabel("Disease Behaviour: ");
    String[] behaviourArray = { "Neutral", "Passive", "Aggressive" };
    JComboBox<String> behaviour = new JComboBox<String>(behaviourArray);

    // Distancing settings
    JLabel addDistancing = new JLabel("Social Distancing: ");
    String[] distancingArray = { "Medium", "Low", "High" };
    JComboBox<String> distancing = new JComboBox<String>(distancingArray);

    // PPE settings
    JLabel addPPE = new JLabel("PPE: ");
    String[] PPEArray = { "Medium", "Low", "High" };
    JComboBox<String> PPE = new JComboBox<String>(PPEArray);

    // Sanitation settings
    JLabel addSanitation = new JLabel("Sanitation: ");
    String[] sanitationArray = { "Medium", "Low", "High" };
    JComboBox<String> sanitation = new JComboBox<String>(sanitationArray);

    // Population number settings
    JLabel addInfectNo = new JLabel("Starting Infection Count");
    JSpinner infectNo = new JSpinner(new SpinnerNumberModel((int)(sizeGrid*sizeGrid)/5, 0, sizeGrid * sizeGrid, 10));

    // Population area settings
    JLabel addGenStep = new JLabel("Generation Step: ");
    JSpinner genStep = new JSpinner(new SpinnerNumberModel(1, 1, 9999999, 1));

    // Population save settings
    JButton saveSettings = new JButton("SAVE");

    // Header population

    int genNum;

    String diseaseNameVar = "Your Disease";

    JPanel generationPanel = new JPanel();
    JPanel diseaseNamePanel = new JPanel();
    JPanel statsPanel = new JPanel();
    JPanel programNamePanel = new JPanel();

    JLabel generationNumber = new JLabel("Generation Number: " + String.valueOf(genNum));

    JLabel diseaseName = new JLabel(diseaseNameVar);

    //JLabel stopwatch = new JLabel("");

    JLabel programName = new JLabel("EPIDEMULATION");

    // Stats needs to be broken into 3 JLabels

    int numberAlive = 0;
    int numberInfected = 0;
    int numberDead = 0;

    JLabel alive = new JLabel(String.valueOf(numberAlive));
    JLabel dead = new JLabel(String.valueOf(numberDead));
    JLabel infected = new JLabel(String.valueOf(numberInfected));

    JPanel stats1 = new JPanel();
    JPanel stats2 = new JPanel();
    JPanel stats3 = new JPanel();

    // Footer population
    JPanel footerPlaceHold = new JPanel();
    JPanel footerPlaceHold2 = new JPanel();
    JPanel footerPlaceHold3 = new JPanel();

    JLabel clock = new JLabel();

    // This method contains a thread which controls the date and time shown in the footer
    public void ClockPane() {
        clock.setText(DateFormat.getDateTimeInstance().format(new Date()));
        Timer timer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clock.setText(DateFormat.getDateTimeInstance().format(new Date()));
                update();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
    }

    // Main population
    JPanel mainPanelMaster = new JPanel();
    JPanel mainPanelSlave = new JPanel();

    JPanel mainHeader = new JPanel();


    JButton rewind = new JButton(rewindImage);
    JButton plot = new JButton(plotImage);
    JButton forward = new JButton(forwardImage);


    Grid simulator;

    // End of population

    // These arrays are iterated through later on for the customisation as the font colour and
    // background colour need to be updated for every single label, button, combobox and panel

    JLabel[] allLabels = { addFont, addTheme, addMod, addBold, addName, addBehaviour, addDistancing, addPPE,
            addSanitation, addInfectNo, addGenStep, generationNumber, diseaseName, programName,
            versionNumber, clock };

    JButton[] allButtons = { saveCustom, saveSettings };

    JComboBox<?>[] allComboBoxes = { font, theme, modifier, behaviour, distancing, PPE, sanitation, };

    JPanel[] allPanels = { custom, settings, header, footer, main, fontPanel, themePanel, modPanel, boldPanel,
            saveCustomPanel, placeHoldCustom, placeHold2Custom, namePanel, behaviourPanel, distancingPanel, PPEPanel,
            popNoPanel, popAreaPanel, sanitationPanel, saveSettingsPanel, placeHoldSettings, placeHold2Settings,
            generationPanel, diseaseNamePanel, statsPanel, programNamePanel, footerPlaceHold,
            footerPlaceHold2, footerPlaceHold3, mainPanelMaster, mainPanelSlave, mainHeader };

    // what i need is an arrayList of all the grids ever made
    // if back is pressed and there are no grids, do nothing
    // if back is pressed and there are grids, go back one grid
    // if forward is pressed and there are grids, go forward one grid
    // if forward is pressed and there are not grids, create a new grid

    // functions needed: createNewGrid(which is passed the previous grid)
    // displayGrid(which is passed the grid to display)
    // createNewGrid() can end with displayGrid(newGrid) as you will never need to
    // create a grid without also displaying it
    // createNewGrid will also need to add the new Grid onto the arrayList

    //Linked list which will contain the grid to iterate through to be displayed
    LinkedList<Grid> allGrids = new LinkedList<Grid>();
    int listIndex = 0;

    int infectedChance;
    int saveChance;
    int killChance;

    // This method displays the grid and by using the simulated grid, and populates the mainPanel with either
    // an alive, infected or dead JLabel to dependent on the state of the corresponding member in the simulator
    public void displayGrid(int gridSize) {
        numberAlive = 0;
        numberInfected = 0;
        numberDead = 0;
        mainPanelSlave.removeAll();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                JLabel label;
                if (simulator.getArray()[i][j].state == 1) {
                    numberInfected++;
                    label = new JLabel(personInfected);
                } else if (simulator.getArray()[i][j].state == 0) {
                    label = new JLabel(personNormal);
                    numberAlive++;
                } else {
                    label = new JLabel(personDead);
                    numberDead++;
                }

                //label.setBorder(blacklinethin);
                mainPanelSlave.add(label);
            }

        }
        GraphPanel.addToDataSet(numberAlive, numberInfected, numberDead, genNum);
    }

    // The method is passed a 2D array of type Member, an x and a y location and returns an integer number
    // of how many infected members surround the located member
    public int getNeighbours(Member[][] array, int locationY, int locationX) {

        int countInfected = 0;
        int[][] possibleI = { { locationY - 1, locationX - 1 }, { locationY - 1, locationX },
                { locationY - 1, locationX + 1 }, { locationY, locationX - 1 }, { locationY, locationX + 1 },
                { locationY + 1, locationX - 1 }, { locationY + 1, locationX }, { locationY + 1, locationX + 1 } };

        for (int i = 0; i < possibleI.length; i++) {
            try {
                if (array[possibleI[i][0]][possibleI[i][1]].state == 1) {
                    countInfected++;
                }
            } catch (Exception e) {
                continue;
            }
        }

        return countInfected;
    }

    // This method is called when the save button is clicked and determines the odds of each of the .infect(),
    // .kill() and .save() methods being called by the main infection algorithm
    public void updateChangeChances() {
        infectedChance =  1;
        killChance = 1;
        saveChance = 1;

        // Disease Behaviour
        if (behaviour.getSelectedItem() == "Passive") {
            infectedChance = infectedChance + 2;
            killChance = killChance + 3;
        } else if (behaviour.getSelectedItem() == "Neutral") {
            infectedChance = infectedChance + 1;
            killChance = killChance + 1;
            saveChance = saveChance + 1;
        } else if (behaviour.getSelectedItem() == "Aggressive") {
            saveChance = saveChance + 2;
        }

        // Social Distancing
        if (distancing.getSelectedItem() == "Low") {
            infectedChance = infectedChance + 1;
        } else if (distancing.getSelectedItem() == "Medium") {
            infectedChance = infectedChance + 2;
            killChance = killChance + 1;
        } else if (distancing.getSelectedItem() == "High") {
            infectedChance = infectedChance + 3;
            killChance = killChance + 3;
        }

        // PPE
        if (PPE.getSelectedItem() == "Low") {
            saveChance = saveChance + 2;
        } else if (PPE.getSelectedItem() == "Medium") {
            infectedChance = infectedChance + 1;
            saveChance = saveChance + 1;
            killChance = killChance + 1;
        } else if (PPE.getSelectedItem() == "High") {
            infectedChance = infectedChance + 2;
            killChance = killChance + 2;
        }

        // Sanitation
        if (sanitation.getSelectedItem() == "Low") {
            saveChance = saveChance + 5;
        } else if (sanitation.getSelectedItem() == "Medium") {
            infectedChance = infectedChance + 1;
            killChance = killChance + 2;
            saveChance = saveChance + 3;
        } else if (sanitation.getSelectedItem() == "High") {
            infectedChance = infectedChance + 2;
            killChance = killChance + 3;
            saveChance = saveChance + 1;
        }
    }

    // This method is the most important in the program as it determines the state of every member in the next
    // generation, given their state in the current generation.
    public void generateNextGrid(Grid oldGrid) {
        Grid newGrid = new Grid(sizeGrid);
        int n;
        Random randomNum = new Random();
        for (int i = 0; i < sizeGrid; i++) {
            for (int j = 0; j < sizeGrid; j++) {
                int infectedNeighbours = getNeighbours(oldGrid.myArray, i, j);

                // my first algorithm
				/*if (simulator.myArray[i][j].state == 2) {
						newGrid.myArray[i][j].kill();
				} else if (infectedNeighbours >= 2) {
					if (simulator.myArray[i][j].state == 1) {
						n = randomNum.nextInt(killChance);
						if (n == 0) {
							newGrid.myArray[i][j].kill();
						}
					} else {
						n = randomNum.nextInt(infectedChance);
						if (n == 0) {
							newGrid.myArray[i][j].infect();
						}
					}
				} else if (infectedNeighbours == 1) {
					n = randomNum.nextInt(infectedChance);
					if (n == 0) {
						newGrid.myArray[i][j].infect();
					} else {
						n = randomNum.nextInt(saveChance);
						if (n == 0) {
							newGrid.myArray[i][j].save();
						}
					}
				} else {
					n = randomNum.nextInt(killChance);
					if (n == 0 && simulator.myArray[i][j].state == 1) {
					//newGrid.myArray[i][j].kill();
					} else {
						newGrid.myArray[i][j].save();
					}
				}*/

                // my second algorithm

                if (simulator.myArray[i][j].state == 2) {
                    newGrid.myArray[i][j].kill();
                } else if (infectedNeighbours > 5) {
                    n = randomNum.nextInt(killChance);
                    if (n == 0) {
                        newGrid.myArray[i][j].kill();
                    }
                } else if (infectedNeighbours >= 3) {
                    if (simulator.myArray[i][j].state == 1) {
                        n = randomNum.nextInt(killChance);
                        if (n == 0) {
                            newGrid.myArray[i][j].kill();
                        }
                    } else {
                        n = randomNum.nextInt(infectedChance);
                        if (n == 0) {
                            newGrid.myArray[i][j].infect();
                        }
                    }
                } else if (infectedNeighbours > 0) {
                    n = randomNum.nextInt(infectedChance);
                    if (n == 0) {
                        newGrid.myArray[i][j].infect();
                    }
                } else {
                    if (simulator.myArray[i][j].state == 1) {
                        n = randomNum.nextInt(2);
                        if (n == 0) {
                            newGrid.myArray[i][j].kill();
                        }
                    } else {
                        newGrid.myArray[i][j].save();
                    }
                }

                // game of life

//				if ((infectedNeighbours == 2 || infectedNeighbours == 3) && simulator.myArray[i][j].state == 1) {
//					newGrid.myArray[i][j].infect();
//				} else if (infectedNeighbours == 3 && simulator.myArray[i][j].state == 0) {
//					newGrid.myArray[i][j].infect();
//				} else {
//					newGrid.myArray[i][j].save();
//				}

            }
        }
        simulator = newGrid;
        addToLinkedList();
    }

    // The first grid needs to be generated differently from every grid after it as it needs to be passed
    // the starting infection count as an additional parameter
    public void generateFirstGrid() {
        genNum = 1;
        simulator = new Grid(sizeGrid);
        simulator.firstGrid(sizeGrid, startInfected);
        displayGrid(sizeGrid);
        addToLinkedList();
        listIndex = 0;
    }

    // returns the next grid in the linked list given the current index number
    public void getNextFromLinkedList(int index) {
        simulator = allGrids.get(listIndex + 1);
        listIndex++;
    }

    // returns the last grid in the linked list given the current index number
    public void getLastFromLinkedList(int index) {
        try {
            simulator = allGrids.get(listIndex - 1);
        } catch (Exception e) {
            return;
        }

        listIndex--;
        genNum--;

    }

    // adds the currently simulated grid to the linked list
    public void addToLinkedList() {
        allGrids.add(simulator);
        listIndex++;
    }

    // clears the entire linked list
    public void clearLinkedList() {
        allGrids.clear();
    }

    // determines whether a new grid needs to be created or if the next grid has already been simulated
    // and can be pulled from the linked list
    public void usualProceedings() {
        genNum++;
        if (allGrids.size() == listIndex + 1) {
            generateNextGrid(simulator);
            displayGrid(sizeGrid);
        } else {
            getNextFromLinkedList(listIndex);
            displayGrid(sizeGrid);
        }
    }

    // updates the header information
    public void update() {
        if (!diseaseNameVar.equals("")) {
            diseaseName.setText(diseaseNameVar);
        }
        alive.setText(String.valueOf(numberAlive));
        infected.setText(String.valueOf(numberInfected));
        dead.setText(String.valueOf(numberDead));
        generationNumber.setText("Generation Number: " + String.valueOf(genNum));
    }

    // updates the font of every component of the program
    public void updateFont() {

        Object fontchoice = font.getSelectedItem();
        String chosenFont = (String) fontchoice;

        int chosenBold;
        if (bold.isSelected()) {
            chosenBold = Font.BOLD;
        } else {
            chosenBold = Font.PLAIN;
        }

        int sizeChoice;
        if (modifier.getSelectedItem() == "Small") {
            sizeChoice = 10;
        } else if (modifier.getSelectedItem() == "Medium") {
            sizeChoice = 13;
        } else {
            sizeChoice = 17;
        }

        for (int i = 0; i < allLabels.length; i++) {
            allLabels[i].setFont(new Font(chosenFont, chosenBold, sizeChoice));
        }
        for (int i = 0; i < allButtons.length; i++) {
            allButtons[i].setFont(new Font(chosenFont, chosenBold, sizeChoice));
        }
        for (int i = 0; i < allComboBoxes.length; i++) {
            allComboBoxes[i].setFont(new Font(chosenFont, chosenBold, sizeChoice));
        }
    }

    // updates the theme of every component of the program
    public void updateTheme() {

        Color fontColour;
        Color backColour;
        if (theme.getSelectedItem() == "Dark") {
            fontColour = Color.WHITE;
            backColour = new Color(30, 30, 30);
        } else if (theme.getSelectedItem() == "Light") {
            fontColour = Color.BLACK;
            backColour = new Color(238, 238, 238);
        } else {
            fontColour = Color.ORANGE;
            backColour = Color.darkGray;
        }

        for (int i = 0; i < allPanels.length; i++) {
            allPanels[i].setBackground(backColour);
        }
        for (int i = 0; i < allLabels.length; i++) {
            allLabels[i].setForeground(fontColour);
        }
        for (int i = 0; i < allComboBoxes.length; i++) {
            allComboBoxes[i].setBackground(backColour);
            allComboBoxes[i].setForeground(fontColour);
        }
        for (int i = 0; i < allButtons.length; i++) {
            allButtons[i].setBackground(backColour);
            allButtons[i].setForeground(fontColour);
        }
        bold.setBackground(backColour);
        bold.setForeground(fontColour);
        cont.setBackground(backColour);
        cont.setForeground(fontColour);
    }

    // updates the disease name variable and the name in the header
    public void updateDiseaseName() {
        diseaseNameVar = name.getText();
        name.setText("");
    }

    // updates the infected count in the program
    public void updateInfectedCount() {
        int currentValue = (int) infectNo.getValue();
        startInfected = currentValue;
        System.out.println(currentValue);
    }

    // --------------------------------------------------------------------------------------------- \\
    // Each of the panels have their own function for adjusting their settings and population below: \\
    // --------------------------------------------------------------------------------------------- \\

    public void frameSettings() {
        setBackground(Color.WHITE);
        setTitle("Epidemulation - Jacob Kettle-Williams 2274");
        setIconImage(virusImage.getImage());
        setLayout(new BorderLayout(5, 5));
        setSize(1000, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void contSettings() {
        cont.setPreferredSize(new Dimension(250, 100));
    }

    public void customSettings() {
        custom.setLayout(new GridLayout(7, 1));

        fontPanel.setLayout(new FlowLayout());
        themePanel.setLayout(new FlowLayout());
        modPanel.setLayout(new FlowLayout());
        boldPanel.setLayout(new FlowLayout());
        saveCustomPanel.setLayout(new FlowLayout());
    }

    public void settingsSettings() {
        settings.setLayout(new GridLayout(10, 1));

        namePanel.setLayout(new FlowLayout());
        behaviourPanel.setLayout(new FlowLayout());
        distancingPanel.setLayout(new FlowLayout());
        PPEPanel.setLayout(new FlowLayout());
        popNoPanel.setLayout(new FlowLayout());
        popAreaPanel.setLayout(new FlowLayout());
        sanitationPanel.setLayout(new FlowLayout());
        saveSettings.setLayout(new FlowLayout());

        bold.setSelected(true);
    }

    public void headerSettings() {
        header.setLayout(new GridLayout(1, 4));
        header.setBorder(blacklineThick);

        statsPanel.setLayout(new GridLayout(1, 3));

        alive.setForeground(Color.BLACK);
        dead.setForeground(Color.WHITE);
        infected.setForeground(Color.BLACK);

        stats1.setLayout(new FlowLayout());
        stats2.setLayout(new FlowLayout());
        stats3.setLayout(new FlowLayout());

        stats1.setBackground(Color.GREEN);
        stats2.setBackground(Color.BLACK);
        stats3.setBackground(Color.RED);
    }

    public void footerSettings() {
        footer.setLayout(new GridLayout(1, 5));
        footer.setBorder(blacklineThick);
    }

    public void mainSettings() {
        main.setLayout(new GridBagLayout());
        mainPanelMaster.setLayout(new BorderLayout());
        mainPanelSlave.setLayout(new GridLayout(sizeGrid, sizeGrid));
        mainPanelSlave.setPreferredSize(new Dimension(500, 500));
    }

    public void populateCustom() {

        // populate each custom panel
        fontPanel.add(addFont);
        fontPanel.add(font);

        themePanel.add(addTheme);
        themePanel.add(theme);

        modPanel.add(addMod);
        modPanel.add(modifier);

        boldPanel.add(addBold);
        boldPanel.add(bold);

        saveCustomPanel.add(saveCustom);

        // populate the actual custom panel
        custom.add(placeHoldCustom);
        custom.add(fontPanel);
        custom.add(themePanel);
        custom.add(modPanel);
        custom.add(boldPanel);
        custom.add(saveCustomPanel);
        custom.add(placeHold2Custom);
    }

    public void populateSettings() {

        // Populate each settings panel
        namePanel.add(addName);
        namePanel.add(name);

        behaviourPanel.add(addBehaviour);
        behaviourPanel.add(behaviour);

        distancingPanel.add(addDistancing);
        distancingPanel.add(distancing);

        PPEPanel.add(addPPE);
        PPEPanel.add(PPE);

        sanitationPanel.add(addSanitation);
        sanitationPanel.add(sanitation);

        popNoPanel.add(addInfectNo);
        popNoPanel.add(infectNo);

        popAreaPanel.add(addGenStep);
        popAreaPanel.add(genStep);

        saveSettingsPanel.add(saveSettings);

        // Populate the actual settings panel
        settings.add(placeHoldSettings);
        settings.add(namePanel);
        settings.add(behaviourPanel);
        settings.add(distancingPanel);
        settings.add(PPEPanel);
        settings.add(sanitationPanel);
        settings.add(popNoPanel);
        settings.add(popAreaPanel);
        settings.add(saveSettingsPanel);
        settings.add(placeHold2Settings);
    }

    public void populateCont() {
        cont.add("Settings", settings);
        cont.add("Customisation", custom);
    }

    public void populateHeader() {

        // populate each header panel
        generationPanel.add(generationNumber);
        diseaseNamePanel.add(diseaseName);

        stats1.add(alive);
        stats2.add(dead);
        stats3.add(infected);

        statsPanel.add(stats1);
        statsPanel.add(stats2);
        statsPanel.add(stats3);
        programNamePanel.add(programName);

        // populate the header panel
        header.add(generationPanel);
        header.add(diseaseNamePanel);
        header.add(statsPanel);
        header.add(programNamePanel);
    }

    public void populateMain() {

        // Populate the grid panel

        mainHeader.add(rewind);
        mainHeader.add(plot);
        mainHeader.add(forward);

        generateFirstGrid();

        // Populate the main panel
        mainPanelMaster.add(mainHeader, BorderLayout.NORTH);
        mainPanelMaster.add(mainPanelSlave, BorderLayout.CENTER);
        main.add(mainPanelMaster);

    }

    public void populateFooter() {
        footer.setPreferredSize(new Dimension(250, 75));

        footer.add(footerPlaceHold);
        footer.add(versionNumber);
        footer.add(footerPlaceHold2);

        footer.add(clock);
        ClockPane();

        footer.add(footerPlaceHold3);

    }

    public void populateFrame() {
        add(header, BorderLayout.NORTH);
        add(footer, BorderLayout.SOUTH);
        add(cont, BorderLayout.WEST);
        add(main, BorderLayout.CENTER);
    }

    // adds the action listeners for each of the buttons
    public void addActionListeners() {

        // Customisation
        saveCustom.addActionListener(this);

        // Settings
        saveSettings.addActionListener(this);

        // Main
        rewind.addActionListener(this);
        plot.addActionListener(this);
        forward.addActionListener(this);
    }

    public GUI() {

        addActionListeners();

        // Settings Customisation
        customSettings();
        settingsSettings();
        headerSettings();
        contSettings();
        mainSettings();
        footerSettings();
        frameSettings();

        // Populating frames
        populateCustom();
        populateHeader();
        populateSettings();
        populateCont();
        populateMain();
        populateFooter();
        populateFrame();

        //updates
        updateFont();
        updateTheme();
        updateChangeChances();

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // Custom Actions
        if (e.getSource() == saveCustom) {
            updateFont();
            updateTheme();
        }
        // Settings Actions
        else if (e.getSource() == saveSettings) {
            updateChangeChances();
            updateDiseaseName();
            updateInfectedCount();
            clearLinkedList();
            GraphPanel.clearDataSet();
            generateFirstGrid();
        }

        // Main Actions
        else if (e.getSource() == rewind) {
            for (int i = 0; i < (int) genStep.getValue(); i++) {
                getLastFromLinkedList(listIndex);
                displayGrid(sizeGrid);
            }
        } else if (e.getSource() == plot) {
            new GraphPanel(diseaseName.getText());
        } else if (e.getSource() == forward) {
            for (int i = 0; i < (int) genStep.getValue(); i++) {
                usualProceedings();
            }
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}