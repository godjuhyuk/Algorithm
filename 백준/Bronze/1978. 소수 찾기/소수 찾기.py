N = int(input())
a = map(int, input().split())

def is_prime(num):
    cnt = 0
    for i in range(2, num):
        cnt += 1 if num%i ==0 else 0
    return True if cnt == 0 and num !=1 else False
ans_cnt = 0
for num in a:
    ans_cnt +=1 if is_prime(num) else 0
print(ans_cnt)
