import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;


public class MyFrame extends JFrame {

    //Tables
    JTable tablePositions = new JTable();
    JTable tableEmployees = new JTable();
    JTable tableContracts = new JTable();
    JTable tableReferences = new JTable();



    //Scrollers
    JScrollPane positionsScroller = new JScrollPane(tablePositions);
    JScrollPane employeesScroller = new JScrollPane(tableEmployees);
    JScrollPane contractsScroller = new JScrollPane(tableContracts);
    JScrollPane referencesScroller = new JScrollPane(tableReferences);


    Connection conn = null;
    PreparedStatement state = null;
    ResultSet result = null;


    int positionId = -1;
    int employeeId = -1;
    int contractId = -1;

    // Tabs
    JTabbedPane tab = new JTabbedPane();

    JPanel positionsPanel = new JPanel();
    JPanel employeesPanel = new JPanel();
    JPanel contractPanel = new JPanel();
    JPanel reference = new JPanel();


    // Panels
    JPanel upPanelPositions = new JPanel();
    JPanel midPanelPositions = new JPanel();
    JPanel downPanelPositions = new JPanel();

    JPanel upPanelEmployees = new JPanel();
    JPanel midPanelEmployees = new JPanel();
    JPanel downPanelEmployees = new JPanel();

    JPanel upPanelContract = new JPanel();
    JPanel midPanelContract = new JPanel();
    JPanel downPanelContract = new JPanel();

    JPanel upPanelReferences = new JPanel();
    JPanel midPanelReferences = new JPanel();
    JPanel downPanelReferences = new JPanel();

    //Label Positions
    JLabel positionsL = new JLabel("Позиция във фирмата: ");
    JLabel searchPositionL = new JLabel("Въведи позиция: ");

    //Label Employees
    JLabel nameL = new JLabel("Име: ");
    JLabel birthDateL = new JLabel("Дата на раждане: ");
    JLabel positionsEmpL = new JLabel("Позиция във фирмата: ");
    JLabel emailL = new JLabel("Email: ");
    JLabel cityL = new JLabel("Град: ");
    JLabel searchEmployeeL = new JLabel("Въведи име: ");

    //Label Contract
    JLabel typeOfContractL = new JLabel("Тип договор: ");
    JLabel dateOfEntryL = new JLabel("Дата на постъпване: ");
    JLabel employeeContractL = new JLabel("Служител");
    JLabel searchContractL = new JLabel("Въведи тип договор: ");


    //Lable References
    JLabel referencesPosition = new JLabel("Позиция във фирмата");
    JLabel referencesContract = new JLabel("Тип договор");

    //TextFields
    JTextField positionsTF = new JTextField();

    JTextField nameTF = new JTextField();

    JTextField emailTF = new JTextField();
    JTextField cityTF = new JTextField();
    JTextField birthDateTF = new JTextField("гггг-мм-дд");
    JTextField dateOfEntryTF = new JTextField("гггг-мм-дд");
    JTextField searchPositionsTF = new JTextField(10);
    JTextField searchEmployeeTF = new JTextField(10);
    JTextField searchContractTF = new JTextField(10);


    String[] contracts = {"Временен" , "Постоянен", "По програма"};
    JComboBox<String> contractsCombo = new JComboBox<String>(contracts);


    JComboBox<String> employeePositionsCombo = new JComboBox<String>();

    JComboBox<String> contractEmployeesCombo = new JComboBox<String>();
    JComboBox<String> referencesPositionsCombo = new JComboBox<String>();
    JComboBox<String> referencesContractCombo = new JComboBox<String>(contracts);

    //Buttons
    JButton addButtonPosition = new JButton("Добавяне");
    JButton delButtonPosition = new JButton("Изтриване");
    JButton editButtonPosition = new JButton("Редактиране");
    JButton searchButtonPosition = new JButton("Търсене");
    JButton refreshButtonPosition = new JButton("Refresh");



    JButton addButtonEmployee = new JButton("Добавяне");
    JButton delButtonEmployee = new JButton("Изтриване");
    JButton editButtonEmployee = new JButton("Редактиране");
    JButton searchButtonEmployee = new JButton("Търсене");
    JButton refreshButtonEmployee = new JButton("Refresh");

    JButton addButtonContract = new JButton("Добавяне");
    JButton delButtonContract = new JButton("Изтриване");
    JButton editButtonContract = new JButton("Редактиране");
    JButton searchButtonContract = new JButton("Търсене");
    JButton refreshButtonContract = new JButton("Refresh");

    JButton referencesButtonSearch = new JButton("Търсене");


    public MyFrame(){
        this.setSize( 800 , 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);




        //Position-----------------------------------------------------------------
        positionsPanel.setLayout(new GridLayout(3,1));

        //upPanelPositions
        upPanelPositions.setLayout(new GridLayout(1,2));
        upPanelPositions.add(positionsL);
        upPanelPositions.add(positionsTF);

        //midPanelPositions
        midPanelPositions.add(addButtonPosition);
        midPanelPositions.add(delButtonPosition);
        midPanelPositions.add(editButtonPosition);
        midPanelPositions.add(searchPositionL);
        midPanelPositions.add(searchPositionsTF);
        midPanelPositions.add(searchButtonPosition);
        midPanelPositions.add(refreshButtonPosition);

        //downPanelPositions
        downPanelPositions.add(positionsScroller);
        positionsScroller.setPreferredSize(new Dimension(700, 170));
        DBHelper.refreshTable("positions", tablePositions);


        //Listeners
        tablePositions.addMouseListener(new TablePositionsListener());
        addButtonPosition.addActionListener(new AddPositionAction());
        delButtonPosition.addActionListener(new DeletePositionAction());
        searchButtonPosition.addActionListener(new SearchPositionAction());
        editButtonPosition.addActionListener(new EditPositionAction());
        refreshButtonPosition.addActionListener(new RefreshPositionAction());


        positionsPanel.add(upPanelPositions);
        positionsPanel.add(midPanelPositions);
        positionsPanel.add(downPanelPositions);



        // Employees-------------------------------------------------------------------

        employeesPanel.setLayout(new GridLayout(3,1));


        //upPanelEmployees
        upPanelEmployees.setLayout(new GridLayout(5,2));
        upPanelEmployees.add(nameL);
        upPanelEmployees.add(nameTF);
        upPanelEmployees.add(positionsEmpL);
        upPanelEmployees.add(employeePositionsCombo);
        upPanelEmployees.add(birthDateL);
        upPanelEmployees.add(birthDateTF);
        upPanelEmployees.add(emailL);
        upPanelEmployees.add(emailTF);
        upPanelEmployees.add(cityL);
        upPanelEmployees.add(cityTF);

        //midPanelEmployees
        midPanelEmployees.add(addButtonEmployee);
        midPanelEmployees.add(delButtonEmployee);
        midPanelEmployees.add(editButtonEmployee);
        midPanelEmployees.add(searchEmployeeL);
        midPanelEmployees.add(searchEmployeeTF);
        midPanelEmployees.add(searchButtonEmployee);
        midPanelEmployees.add(refreshButtonEmployee);

        //downPanelEmployees
        downPanelEmployees.add(employeesScroller);
        employeesScroller.setPreferredSize(new Dimension(700, 170));
        refreshEmployeePositionsCombo();
        refreshTableEmployee();

        //Listeners
        tableEmployees.addMouseListener(new TableEmployeesListener());
        addButtonEmployee.addActionListener(new AddEmployeeAction());
        delButtonEmployee.addActionListener(new DeleteEmployeeAction());
        editButtonEmployee.addActionListener(new EditEmployeeAction());
        searchButtonEmployee.addActionListener(new SearchEmployeeAction());
        refreshButtonEmployee.addActionListener(new RefreshEmployeeAction());



        employeesPanel.add(upPanelEmployees);
        employeesPanel.add(midPanelEmployees);
        employeesPanel.add(downPanelEmployees);



        //Contracts-----------------------------------------------------------------------------
        contractPanel.setLayout(new GridLayout(3,1));


        //upPanelContract
        upPanelContract.setLayout(new GridLayout(3,2));
        upPanelContract.add(typeOfContractL);
        upPanelContract.add(contractsCombo);
        upPanelContract.add(dateOfEntryL);
        upPanelContract.add(dateOfEntryTF);
        upPanelContract.add(employeeContractL);
        upPanelContract.add(contractEmployeesCombo);


        //midPanelContract
        midPanelContract.add(addButtonContract);
        midPanelContract.add(delButtonContract);
        midPanelContract.add(editButtonContract);
        midPanelContract.add(searchContractL);
        midPanelContract.add(searchContractTF);
        midPanelContract.add(searchButtonContract);
        midPanelContract.add(refreshButtonContract);

        //downPanelContract
        downPanelContract.add(contractsScroller);
        contractsScroller.setPreferredSize(new Dimension(700, 170));
        refreshTableContracts();
        refreshContractEmployeesCombo();


        //Listeneres
        tableContracts.addMouseListener(new TableContractsListener());
        addButtonContract.addActionListener(new AddContractAction());
        delButtonContract.addActionListener(new DeleteContractAction());
        editButtonContract.addActionListener(new EditContractAction());
        searchButtonContract.addActionListener(new SearchContractAction());
        refreshButtonContract.addActionListener(new RefreshContractAction());


        contractPanel.add(upPanelContract);
        contractPanel.add(midPanelContract);
        contractPanel.add(downPanelContract);
        //References--------------------------------------------------------------------------------------------------------------

        reference.setLayout(new GridLayout(3,1));
        //upPanel

        upPanelReferences.setLayout(new GridLayout(2,2));
        upPanelReferences.add(referencesPosition);
        upPanelReferences.add(referencesPositionsCombo);
        upPanelReferences.add(referencesContract);
        upPanelReferences.add(referencesContractCombo);

        //midPanel
        midPanelReferences.add(referencesButtonSearch);
        refreshReferencesPositionsCombo();



        //downPanel
        downPanelReferences.add(referencesScroller);
        referencesScroller.setPreferredSize(new Dimension(700, 170));

        //listeners
        referencesButtonSearch.addActionListener(new ReferencesAction());

        reference.add(upPanelReferences);
        reference.add(midPanelReferences);
        reference.add(downPanelReferences);


        tab.add(positionsPanel, "Позиции във фирмата");
        tab.add(employeesPanel, "Служители");
        tab.add(contractPanel, "Договор");
        tab.add(reference, "Справки");
        this.add(tab);





        this.setVisible(true);
    }//end Constructor

    //Clear Forms------------------------------------------------------------------------------------------------
    public void clearPositionForm() {
        positionsTF.setText("");
    }//end clearPositionForm


    public void clearEmployeeForm() {
        positionsTF.setText("");
        nameTF.setText("");
        emailTF.setText("");
        cityTF.setText("");
        birthDateTF.setText("гггг-мм-дд");
    }

    public void clearContractForm() {
        dateOfEntryTF.setText("гггг-мм-дд");
    }


    public void clearSearchPositionTF(){
        searchPositionsTF.setText("");
    }
    public void clearSearchEmployeeTF(){
        searchEmployeeTF.setText("");
    }
    public void clearSearchContractTF(){
        searchContractTF.setText("");
    }


    //Refresh methods -------------------------------------------------------------------------------------
    public void refreshEmployeePositionsCombo() {
        employeePositionsCombo.removeAllItems();

        String sql="select position_id, position from positions";
        conn=DBHelper.getConnection();
        String item="";

        try {
            state=conn.prepareStatement(sql);
            result=state.executeQuery();
            while(result.next()) {
                item=result.getObject(1).toString()+"."+result.getObject(2).toString();
                employeePositionsCombo.addItem(item);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//end method refreshEmployeeCombo()
    public void refreshReferencesPositionsCombo() {
        referencesPositionsCombo.removeAllItems();

        String sql="select position from positions";
        conn=DBHelper.getConnection();
        String item="";

        try {
            state=conn.prepareStatement(sql);
            result=state.executeQuery();
            while(result.next()) {
                item=result.getObject(1).toString();
                referencesPositionsCombo.addItem(item);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//end method refreshReferencesPositionsCombo()

    public void refreshContractEmployeesCombo() {
        contractEmployeesCombo.removeAllItems();

        String sql="select employee_id, name from employees";
        conn=DBHelper.getConnection();
        String item="";

        try {
            state=conn.prepareStatement(sql);
            result=state.executeQuery();
            while(result.next()) {
                item=result.getObject(1).toString()+"."+result.getObject(2).toString();
                contractEmployeesCombo.addItem(item);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//end method refreshEmployeeCombo()

    public void refreshTableEmployee() {
        conn=DBHelper.getConnection();
        String str="SELECT employee_id, position, name, birth_date, email, city FROM EMPLOYEES E JOIN POSITIONS P ON E.POSITION_ID = P.POSITION_ID";

        try {
            state=conn.prepareStatement(str);
            result=state.executeQuery();
            tableEmployees.setModel(new MyModelInteliJ(result));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } // end method refreshTableEmployee()

    public void refreshTableContracts() {
        conn=DBHelper.getConnection();
        String str="SELECT contract_id, type_of_contract, date_of_entry, name FROM EMPLOYEES E JOIN CONTRACT C ON E.EMPLOYEE_ID = C.EMPLOYEE_ID";

        try {
            state=conn.prepareStatement(str);
            result=state.executeQuery();
            tableContracts.setModel(new MyModelInteliJ(result));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } // end method refreshTableContracts()


    //Positions Listeners ----------------------------------------------------------------------------------------------------------------
    //AddPosition

    class AddPositionAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String position = positionsTF.getText();

            conn = DBHelper.getConnection();
            try {
                state = conn.prepareStatement("insert into positions values(null,?);");
                state.setString(1, position);


                state.execute();
                DBHelper.refreshTable("positions", tablePositions);
                refreshEmployeePositionsCombo();
                refreshReferencesPositionsCombo();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            clearPositionForm();

        }//end method actionPerformed

    }//end class AddPositionAction

    //DeleteAction
    class DeletePositionAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBHelper.getConnection();
            String sql = "delete from positions where position_id=?";
            try {
                state = conn.prepareStatement(sql);
                state.setInt(1, positionId);


                state.execute();
                DBHelper.refreshTable("positions", tablePositions);
                refreshEmployeePositionsCombo();
                refreshTableEmployee();
                refreshReferencesPositionsCombo();
                positionId = -1;
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                //e1.printStackTrace();
                JOptionPane.showMessageDialog(null,  "В таблица служители има запис с елмента, който искате да изтриете","Предупреждение", JOptionPane.INFORMATION_MESSAGE);
            }
            clearPositionForm();
        }

    } //end class DeletePositionAction


    class EditPositionAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String position = positionsTF.getText();

            conn = DBHelper.getConnection();
            try {
                state = conn.prepareStatement("update positions set position=? where position_id=?");
                state.setString(1, position);
                state.setInt(2,positionId);


                state.execute();
                DBHelper.refreshTable("positions", tablePositions);
                refreshEmployeePositionsCombo();
                refreshTableEmployee();
                refreshReferencesPositionsCombo();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            clearPositionForm();

        }//end method actionPerformed

    }//end class EditPositionAction


    class SearchPositionAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String position = searchPositionsTF.getText();
            conn = DBHelper.getConnection();
            String str = "select * from positions where lower(position) like lower('%"+ position +"%')";
            try {
                state = conn.prepareStatement(str);

                result=state.executeQuery();
                tablePositions.setModel(new MyModelInteliJ(result));

            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();


            }
            clearSearchPositionTF();
        }//end method actionPerformed

    }//end class SearchPositionAction

    class RefreshPositionAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            DBHelper.refreshTable("positions", tablePositions);
        }
    }


    //EmployeesListeners--------------------------------------------------------------------------------------------------------

    //AddAction
    class AddEmployeeAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameTF.getText();
            String position =employeePositionsCombo.getSelectedItem().toString();
            String birth_date = birthDateTF.getText();
            String email = emailTF.getText();
            String city = cityTF.getText();

            String str = "insert into employees values(null,?,?,?,?,?);";
            conn= DBHelper.getConnection();

            try {
                state = conn.prepareStatement(str);
                state.setString(2, name);
                state.setInt(1, Integer.parseInt(position.substring(0, position.indexOf('.'))));
                state.setDate(3, java.sql.Date.valueOf(birth_date));
                state.setString(4, email);
                state.setString(5, city);

                state.execute();

                refreshTableEmployee();
                refreshContractEmployeesCombo();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            clearEmployeeForm();
        }

    }//end class AddEmployeeAction

    //DeleteAction
    class DeleteEmployeeAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBHelper.getConnection();
            String str = "delete from employees where employee_id=?";
            try {
                state = conn.prepareStatement(str);
                state.setInt(1, employeeId);


                state.execute();
                refreshTableEmployee();
                refreshContractEmployeesCombo();
                refreshTableContracts();
                employeeId = -1;
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                //e1.printStackTrace();
                JOptionPane.showMessageDialog(null,  "В таблица договори има запис с елмента, който искате да изтриете","Предупреждение", JOptionPane.INFORMATION_MESSAGE);
            }
            clearEmployeeForm();
        }

    } //end class DeleteEmployeeAction

    class EditEmployeeAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String name = nameTF.getText();
            String position =employeePositionsCombo.getSelectedItem().toString();
            String birth_date = birthDateTF.getText();
            String email = emailTF.getText();
            String city = cityTF.getText();

            String str = "update employees set position_id=?,name=?, birth_date=?, email=?, city=? where employee_id=?";

            conn = DBHelper.getConnection();
            try {
                state = conn.prepareStatement(str);

                state.setInt(1, Integer.parseInt(position.substring(0, position.indexOf('.'))));
                state.setString(2, name);
                state.setString(3, birth_date);
                state.setString(4, email);
                state.setString(5, city);
                state.setInt(6, employeeId);
                state.execute();

                refreshTableEmployee();
                refreshContractEmployeesCombo();
                refreshTableContracts();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            clearPositionForm();

        }//end method actionPerformed

    }//end class EditEmployeeAction
    class SearchEmployeeAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = searchEmployeeTF.getText();
            conn = DBHelper.getConnection();
            String str = "SELECT employee_id, position, name, birth_date, email, city FROM EMPLOYEES E JOIN POSITIONS P ON E.POSITION_ID = P.POSITION_ID where lower(name) like lower('%"+ name+"%')";
            try {
                state = conn.prepareStatement(str);
                //state.setString(1, name);

                result=state.executeQuery();


                tableEmployees.setModel(new MyModelInteliJ(result));

                refreshEmployeePositionsCombo();


            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            clearSearchEmployeeTF();

        }//end method actionPerformed

    }//end class SearchEmployeeAction


    class RefreshEmployeeAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTableEmployee();
            refreshContractEmployeesCombo();
            refreshEmployeePositionsCombo();
        }
    }
    //ContractsListeners--------------------------------------------------------------------------------------------------------

    //AddAction
    class AddContractAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String typeContract =contractsCombo.getSelectedItem().toString();
            String date_of_entry = dateOfEntryTF.getText();
            String contractEmp =contractEmployeesCombo.getSelectedItem().toString();
            String str = "insert into contract values(null,?,?,?);";
            conn= DBHelper.getConnection();

            try {
                state = conn.prepareStatement(str);
                state.setString(1, typeContract);
                state.setDate(2, java.sql.Date.valueOf(date_of_entry));
                state.setInt(3, Integer.parseInt(contractEmp.substring(0, contractEmp.indexOf('.'))));

                state.execute();
                clearContractForm();
                refreshTableContracts();
                refreshContractEmployeesCombo();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }//end class AddContractAction

    //DeleteAction
    class DeleteContractAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBHelper.getConnection();
            String str = "delete from contract where contract_id=?";
            try {
                state = conn.prepareStatement(str);
                state.setInt(1, contractId);


                state.execute();
                refreshTableContracts();
                refreshContractEmployeesCombo();
                contractId = -1;
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            clearContractForm();
        }

    } //end class DeleteContractAction

    class EditContractAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String typeContract =contractsCombo.getSelectedItem().toString();
            String date_of_entry = dateOfEntryTF.getText();
            String contractEmp =contractEmployeesCombo.getSelectedItem().toString();
            String str = "update contract set type_of_contract=?, date_of_entry=?, employee_id=? where contract_id=?";
            conn= DBHelper.getConnection();

            try {
                state = conn.prepareStatement(str);
                state.setString(1, typeContract);
                state.setString(2, date_of_entry);
                state.setInt(3, Integer.parseInt(contractEmp.substring(0, contractEmp.indexOf('.'))));
                state.setInt(4,contractId);

                state.execute();
                clearContractForm();
                refreshTableContracts();
                refreshContractEmployeesCombo();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }//end class AddContractAction


    class SearchContractAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String contract = searchContractTF.getText();
            conn = DBHelper.getConnection();
            String str = "SELECT contract_id, type_of_contract, date_of_entry, name FROM EMPLOYEES E JOIN CONTRACT C ON E.EMPLOYEE_ID = C.EMPLOYEE_ID where lower(type_of_contract) like lower('%"+contract+"%')";
            try {
                state = conn.prepareStatement(str);

                result=state.executeQuery();

                tableContracts.setModel(new MyModelInteliJ(result));

                refreshContractEmployeesCombo();

            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            clearSearchContractTF();
        }//end method actionPerformed

    }//end class SearchContractAction

    class RefreshContractAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTableContracts();
            refreshContractEmployeesCombo();
        }
    } //end class RefreshContractAction

    //MouseListeners----------------------------------------------------------------------------------------------------------
    class TablePositionsListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            int row = tablePositions.getSelectedRow();
            positionId = Integer.parseInt(tablePositions.getValueAt(row, 0).toString());

            positionsTF.setText(tablePositions.getValueAt(row,1).toString());
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

    }//end TablePositionsListener

    class TableEmployeesListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            conn = DBHelper.getConnection();
            int row = tableEmployees.getSelectedRow();
            employeeId = Integer.parseInt(tableEmployees.getValueAt(row, 0).toString());

            String employeePositionsC = null;
            String str = "select * from positions where position=?";
            try {
                state = conn.prepareStatement(str);
                state.setString(1, tableEmployees.getValueAt(row, 1).toString());
                result = state.executeQuery();
                while(result.next()) {
                    employeePositionsC = result.getObject(1).toString() + "." + result.getObject(2).toString();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            employeePositionsCombo.setSelectedItem(employeePositionsC);
            nameTF.setText(tableEmployees.getValueAt(row, 2).toString());
            birthDateTF.setText(tableEmployees.getValueAt(row, 3).toString());
            emailTF.setText(tableEmployees.getValueAt(row, 4).toString());
            cityTF.setText(tableEmployees.getValueAt(row, 5).toString());
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

    }//end TableEmployeesListener

    class TableContractsListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            int row = tableContracts.getSelectedRow();
            contractId = Integer.parseInt(tableContracts.getValueAt(row, 0).toString());

            contractsCombo.setSelectedItem(tableContracts.getValueAt(row, 1).toString());
            dateOfEntryTF.setText(tableContracts.getValueAt(row, 2).toString());
            String contractEmployeesC = null;
            String str = "select employee_id, name from employees where name=?";
            try {
                state = conn.prepareStatement(str);
                state.setString(1, tableContracts.getValueAt(row, 3).toString());
                result = state.executeQuery();
                while(result.next()) {
                    contractEmployeesC = result.getObject(1).toString() + "." + result.getObject(2).toString();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            contractEmployeesCombo.setSelectedItem(contractEmployeesC);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

    }//end TableContractsListener


    //references search

    class ReferencesAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String item = referencesPositionsCombo.getSelectedItem().toString();
            String contract = referencesContractCombo.getSelectedItem().toString();
            conn = DBHelper.getConnection();
            String str = "SELECT position, name, email, type_of_contract  FROM EMPLOYEES E JOIN POSITIONS P ON E.POSITION_ID = P.POSITION_ID join CONTRACT C on E.EMPLOYEE_ID = C.EMPLOYEE_ID where position=? and type_of_contract =?";
            try {
                state = conn.prepareStatement(str);
                state.setString(1, item);
                state.setString(2, contract);

                result=state.executeQuery();

                tableReferences.setModel(new MyModelInteliJ(result));
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();

            }

        }//end method actionPerformed

    }//end class ReferencesAction

}//end class MyFrame
