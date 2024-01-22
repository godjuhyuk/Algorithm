import sys
input = sys.stdin.readline
N= int(input())
distance = list(map(int, input().split()))
price = list(map(int, input().split()))
# N= 4
# distance = [2,3,1]
# price = [5,2,4,1]
# distance = [2,3,1]
# price = [5,2,1,1]
ans, all_dist = 0, 0
i, j =  0, 1
while j < N:
    if price[i] <= price[j] and j < N-1:
        j+=1
    else:
        for k in range(i, j):
            all_dist += distance[k]
        # print(i,j, all_dist, price[i])
        ans += price[i] * all_dist
        i = j
        j = i+1
        all_dist = 0
print(ans)
