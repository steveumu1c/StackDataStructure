package cms350p1v3;

import java.awt.*;

import java.awt.event.*;

import java.util.*;

import javax.swing.*;

public class CalcInputGuip1 extends JFrame

		implements ActionListener

{

//Declare the object of JTextField

	JTextField userText, resultText;

//Declare the labels

	JLabel inputLabel1, resultLabel2, helpLabel1;

//Declare 2 Jpanels that will make up the GUI

	JPanel inputPanel, resultPanel;
	JButton calcButton;

//Declare stack as object

	Stack<Object> obj;

//Constructor call

	CalcInputGuip1()

	{

//header name

		super("Infix Expression Evaluator");

//Instantiate First panel

		inputPanel = new JPanel(new FlowLayout());

//Instantiate the second panel

		resultPanel = new JPanel(new FlowLayout());

//set layout

		setLayout(new GridLayout(3, 2));

//Instantiate the textfield to enter string.

		userText = new JTextField(25);

//Instantiate the textfield to display result.

		resultText = new JTextField(15);

//Insert instructions in the input area

		inputLabel1 = new JLabel(

				"Enter Infix Expression ");

//Name the calculate button and instantiate

		calcButton = new JButton("Calculate");

//Track the calcbutton

		calcButton.addActionListener(this);

//Instantiate the result label for output

		resultLabel2 = new JLabel("Ouput ",

				SwingConstants.CENTER);

//Adding all the panels to the jframe

		add(inputPanel);

		add(resultPanel);

//Add input to panel

		inputPanel.add(inputLabel1);

//Include text from the tester

		inputPanel.add(userText);

//Add calculate button to input panel

		inputPanel.add(calcButton);

//Add result label to 2nd panel

		resultPanel.add(resultLabel2);

//display the results in the fieldd

		resultPanel.add(resultText);

//Instantiate a new stack for the process

		obj = new Stack<Object>();

	}

//Jumping off point for program

	public static void main(String args[])

	{

		CalcInputGuip1 Calc = new CalcInputGuip1();

		Calc.setVisible(true);
		Calc.setDefaultCloseOperation(EXIT_ON_CLOSE);
//Set the size of applet

		Calc.setSize(380, 290);

	}


//Determine if "infix expressions of unsigned integers" can be evaluated due to concern pairing of ( and )
	//essentially making sure that each ( has a )
	boolean isInputAdequate(String str)

	{
		int index = 0;
		boolean f = false;
//Get the length of an expression

		int length = str.length();
		try
//Wrap in try catch to handle exceptions
		{

//Is the index shorter than length of the expression?
			while (index <length && !f)
			{
//Select the char at the index for analysis
				char analyte = str.charAt(index);
//Switch on the possibilities
				switch (analyte)
				{
				case '(':
//If left brace push the brace in stack
					obj.push(new Character(analyte));
					break;
//case to check right brace
				case ')':
//pop the elements from stack
					char rightBrace = (char) obj.pop();
//condition to check that braces are balanced
					if (rightBrace != '(')
						f = true;
					break;
				default:
//skip all elements will occur for most analytes
					break;
				}
				index++;
		}
	}
//Start the catch block
		catch (EmptyStackException e)
		{
//Catches the exception
			f = true;
		}
//return the calculated value
		return obj.empty() && !f;
	}

//Main function at each button push

	public void actionPerformed(ActionEvent arg0)

	{

//Instantiate the object of evaluation class

		EvaltheInput eval = new EvaltheInput();

//copy the user entered string in expression

		String str = userText.getText();

//Check whether expression is adequate

		if (isInputAdequate(str))

		{

			try

			{

//Storing the result in a integer variable

				int result = eval.evaluate(str);

//Check if result is -1

				if (result == -1)

//display expression invalid

					resultLabel2.setText(

							"Result: Expresssion is not correct.");

				else

//Display the result

				{

					resultLabel2.setText("Result ");

//Copy the text in the text field

					resultText.setText(

							Integer.toString(result));

				}

			}

//Start the catch block

//check that value should not be divided by zero

			catch (ArithmeticException | DivideByZero e)

			{

//Display the message

				JOptionPane.showMessageDialog(this,

						"You cannot divide by 0",

						"Divide by zero error",

						JOptionPane.ERROR_MESSAGE);

				/*
				 * } catch (DivideByZero e) { // TODO Auto-generated catch block
				 * e.printStackTrace();
				 */
			}

		}

//Check whether expression is balanced or not.

		else

//Display the message.

			resultLabel2.setText(

					"Input Expression cannot be evaluated.");

//String remain in the text field

		userText.setText(str);

	}

}