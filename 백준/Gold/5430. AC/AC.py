import sys
input_ = [x.strip() for x in sys.stdin.readlines()[1:]]
def sol(method, len_, queue):
    front_idx, back_idx = 0, len_-1
    is_reverse = False
    for func in method:
        if func == 'R':
            is_reverse = not is_reverse
        else:
            if back_idx < front_idx:
                return 'error'
            elif is_reverse:
                back_idx -= 1
            else:
                front_idx += 1
    if is_reverse:
        return f'[{",".join([queue[i] for i in range(back_idx, front_idx-1, -1)])}]'
    else:    
        return f'[{",".join([i for i in queue[front_idx:back_idx+1]])}]'
for i in range(0, len(input_)-1, 3):
    method, len_, queue = input_[i], int(input_[i+1]), input_[i+2][1:-1].split(',')
    print(sol(method.replace('RR', ''), len_, queue))
