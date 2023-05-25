# Nfa-builder-simulator
firs part of the code builds an nfa, second half simulates it 
build.java takes an input regex containing only a-z and 0-9, |, * and () and outputs on the first line: number of states, number of accept states and number of transitions.
second output line shows indexes of the states that are accepting.
and for the next n lines, i-th line shows how many transitions i-th state has, and those transitions. 
example: 
  input:  (a|b)*(c|())
  output: 2 2 3
          0 1
          3 a 0 b 0 c 1
          0     

second part of the code runs a simulation of the nfa. on the first line of input, it takes on a string. and on the next line it takes a nfa written in the format of a builds output.
this code outputs a string whose i-th character shows wether nfa was on accepting state on i-th stage or not.
example: 
input: aababacab
       2 2 3
       0 1
       3 a 0 b 0 c 1
       0
output: YYYYYYYNN
