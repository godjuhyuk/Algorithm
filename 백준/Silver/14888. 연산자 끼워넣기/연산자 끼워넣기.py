import sys
input = sys.stdin.readline
N= int(input())
number_arr = list(map(int, input().split()))
oper_input = list(map(int, input().split()))
operator = ['+', '-', 'x', '/']
_max = -1000000000
_min = 1000000000


def calculate(result, num2,  cnt, oper_input, operate = ''):
    global _max, _min
    if operate == '+':
        result = result + num2
    elif operate == '-':
        result = result - num2
    elif operate == 'x':
        result = result * num2
    elif operate == '/' and result < 0:
        result = -1 * int(abs(result)//num2)
    elif operate == '/' and result >= 0:
        result = int(result // num2)
    
    if cnt == N-1:
        if result > _max:
            _max = result
        if result < _min:
            _min = result
        return
    
    for i in range(len(oper_input)):
        if oper_input[i] > 0:
            oper_input[i] -= 1
            cnt +=1 
            calculate(result, number_arr[cnt], cnt, oper_input, operator[i])
            oper_input[i] +=1
            cnt -= 1
    
calculate(number_arr[0], 0,  0, oper_input)
print(_max, _min)