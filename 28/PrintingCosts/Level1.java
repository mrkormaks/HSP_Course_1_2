import java.util.*;

public class Level1 {
  public static int PrintingCosts(String Line) {
    int totalCost = 0;

    int[] costs = new int[128];

    costs[' '] = 0;   costs['!'] = 9;   costs['"'] = 6;   costs['#'] = 24;  costs['$'] = 29;
    costs['%'] = 22;  costs['&'] = 24;  costs['\''] = 3;  costs['('] = 12;  costs[')'] = 12;
    costs['*'] = 17;  costs['+'] = 13;  costs[','] = 7;   costs['-'] = 7;   costs['.'] = 4;
    costs['/'] = 10;  costs['0'] = 22;  costs['1'] = 19;  costs['2'] = 22;  costs['3'] = 23;
    costs['4'] = 21;  costs['5'] = 27;  costs['6'] = 26;  costs['7'] = 16;  costs['8'] = 23;
    costs['9'] = 26;  costs[':'] = 8;   costs[';'] = 11;  costs['<'] = 10;  costs['='] = 14;
    costs['>'] = 10;  costs['?'] = 15;  costs['@'] = 32;  costs['A'] = 24;  costs['B'] = 29;
    costs['C'] = 20;  costs['D'] = 26;  costs['E'] = 26;  costs['F'] = 20;  costs['G'] = 25;
    costs['H'] = 25;  costs['I'] = 18;  costs['J'] = 18;  costs['K'] = 21;  costs['L'] = 16;
    costs['M'] = 28;  costs['N'] = 25;  costs['O'] = 26;  costs['P'] = 23;  costs['Q'] = 31;
    costs['R'] = 28;  costs['S'] = 25;  costs['T'] = 16;  costs['U'] = 23;  costs['V'] = 19;
    costs['W'] = 26;  costs['X'] = 18;  costs['Y'] = 14;  costs['Z'] = 22;  costs['['] = 18;
    costs['\\'] = 10; costs[']'] = 18;  costs['^'] = 7;   costs['_'] = 8;   costs['`'] = 3;
    costs['a'] = 23;  costs['b'] = 25;  costs['c'] = 17;  costs['d'] = 25;  costs['e'] = 23;
    costs['f'] = 18;  costs['g'] = 30;  costs['h'] = 21;  costs['i'] = 15;  costs['j'] = 20;
    costs['k'] = 21;  costs['l'] = 16;  costs['m'] = 22;  costs['n'] = 18;  costs['o'] = 20;
    costs['p'] = 25;  costs['q'] = 25;  costs['r'] = 13;  costs['s'] = 21;  costs['t'] = 17;
    costs['u'] = 17;  costs['v'] = 13;  costs['w'] = 19;  costs['x'] = 13;  costs['y'] = 24;
    costs['z'] = 19;  costs['{'] = 18;  costs['|'] = 12;  costs['}'] = 18;  costs['~'] = 9;

    for (char c : Line.toCharArray()) {
      if (c < 128) {
        totalCost += costs[c];
      } else {
        totalCost += 23;
      }
    }

    return totalCost;
  }
}