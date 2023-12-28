#include <iostream>
using namespace std;

int fun1_2_3(int n)
{
	if (n == 3)
		return 4;
	if (n == 2)
		return 2;
	if (n == 1)
		return 1;

	if (n > 1)
		return fun1_2_3(n - 1) + fun1_2_3(n - 2) + fun1_2_3(n - 3);

}

int main()
{
	int t;
	cin >> t;

	int n;

	while (t)
	{
		cin >> n;
		cout << fun1_2_3(n) << endl;
		t--;
	}
}