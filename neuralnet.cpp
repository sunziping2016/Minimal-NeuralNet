#include <iostream>
#include <cstring>
#include <functional>
#include <fstream>
#include <sstream>
#include <ctime>
#include <tuple>
#include <iterator>
#include <random>
#include <initializer_list>

using namespace std;

// Minimal matrix class
// Require: memset and memcpy in <cstring>, initializer_list in <initializer_list>, sqrt in <cmath>,
//          function in <functional>, ostream and istream in <iostream>
struct Matrix {
	size_t rows, cols;
	double *matrix;
	double &at(int row, int col) { return matrix[row * cols + col]; }
	double at(int row, int col) const { return matrix[row * cols + col]; }
	Matrix() : rows(0), cols(0), matrix(nullptr) { }
	~Matrix() { if (matrix) delete []matrix; }
	Matrix(size_t rows, size_t cols): rows(rows), cols(cols), matrix(new double[rows * cols]) {}
	Matrix(size_t rows, size_t cols, double value): rows(rows), cols(cols), matrix(new double[rows * cols])
	{ for(size_t i = 0; i < rows * cols; ++i) matrix[i] = value; }
	Matrix(size_t rows, size_t cols, initializer_list<double> array): rows(rows), cols(cols), matrix(new double[rows * cols])
	{ size_t i = 0; for(auto iter = array.begin(); iter != array.end(); ++iter) matrix[i++] = *iter; }
	template<typename InputIterator>
	Matrix(size_t rows, size_t cols, InputIterator first, InputIterator last): rows(rows), cols(cols), matrix(new double[rows * cols])
	{ size_t i = 0; for(auto iter = first; iter != last; ++iter) matrix[i++] = *iter; }
	Matrix(const Matrix &orig): rows(orig.rows), cols(orig.cols), matrix(new double[rows * cols])
	{ memcpy(matrix, orig.matrix, sizeof(double) * rows * cols); }
	Matrix(Matrix &&orig): rows(orig.rows), cols(orig.cols), matrix(orig.matrix)
	{ orig.rows = orig.cols = 0; orig.matrix = nullptr; }
	Matrix &operator = (const Matrix &orig) {
		if (this != &orig) {
			if (matrix) delete []matrix;
			rows = orig.rows; cols = orig.cols; matrix = new double[rows * cols];
			memcpy(matrix, orig.matrix, sizeof(double) * rows * cols);
		}
		return *this;
	}
	Matrix &operator = (Matrix &&orig) {
		if (this != &orig) {
			if (matrix) delete []matrix;
			rows = orig.rows; cols = orig.cols; matrix = orig.matrix;
			orig.rows = orig.cols = 0; orig.matrix = nullptr;
		}
		return *this;
	}
	size_t size() const { return rows * cols; }
	double magnitude() const {
		double sum = 0;
		for (size_t i = 0; i < size(); ++i)
			sum += matrix[i] * matrix[i];
		return sqrt(sum);
	}
	Matrix submatrix(size_t startrow, size_t endrow, size_t startcol, size_t endcol) const {
		Matrix matrix(endrow - startrow, endcol - startcol);
		for (size_t i = startrow; i < endrow; ++i)
			for (size_t j = startcol; j < endcol; ++j)
				matrix.at(i - startrow, j - startcol) = this->at(i, j);
		return matrix;
	}
	static Matrix transpose(const Matrix &matrix);
	static Matrix hadamard(const Matrix &lhs, const Matrix &rhs);
	static Matrix kronecker(const Matrix &lhs, const Matrix &rhs);
	static Matrix horcat(const Matrix &lhs, const Matrix &rhs);
	static Matrix apply(const Matrix &matrix, function<double(double)> func);
};
ostream &operator << (ostream &out, const Matrix &matrix) {
	for (size_t i = 0; i < matrix.rows; ++i) {
		for (size_t j = 0; j < matrix.cols - 1; ++j)
			out << matrix.at(i, j) << "\t";
		if (matrix.cols > 0) out << matrix.at(i, matrix.cols - 1);
		out << '\n';
	}
	return out;
}
istream &operator >> (istream &in, Matrix &matrix) {
	for (size_t i = 0; i < matrix.rows; ++i)
		for (size_t j = 0; j < matrix.cols; ++j)
			in >> matrix.at(i, j);
	return in;
}
inline Matrix &operator += (Matrix &lhs, const Matrix &rhs) {
	//assert(lhs.rows == rhs.rows && lhs.cols == rhs.cols);
	for (size_t i = 0; i < lhs.size(); ++i) lhs.matrix[i] += rhs.matrix[i]; return lhs;
}
inline Matrix &operator -= (Matrix &lhs, const Matrix &rhs) {
	//assert(lhs.rows == rhs.rows && lhs.cols == rhs.cols);
	for (size_t i = 0; i < lhs.size(); ++i) lhs.matrix[i] -= rhs.matrix[i]; return lhs;
}
inline Matrix operator + (const Matrix &lhs, const Matrix &rhs)
{ Matrix result(lhs); result += rhs; return result; }
inline Matrix operator - (const Matrix &lhs, const Matrix &rhs)
{ Matrix result(lhs); result -= rhs; return result; }
inline Matrix &operator *= (Matrix &lhs, double num)
{ for (size_t i = 0; i < lhs.size(); ++i) lhs.matrix[i] *= num; return lhs; }
inline Matrix operator * (const Matrix &lhs, double num)
{ Matrix result(lhs); result *= num; return result; }
inline Matrix operator * (double num, const Matrix &rhs)
{ return rhs * num; }
Matrix &operator *= (Matrix &lhs, const Matrix &rhs) {
	//assert(lhs.cols == rhs.rows);
	double *temp = new double[lhs.rows * rhs.cols];
	memset(temp, 0, sizeof(double) * lhs.rows * rhs.cols);
	for (size_t row = 0; row < lhs.rows; ++row)
		for (size_t col = 0; col < rhs.cols; ++col)
			for (size_t inner = 0; inner < lhs.cols; ++inner)
				temp[row * rhs.cols + col] += lhs.at(row, inner) * rhs.at(inner, col);
	lhs.cols = rhs.cols;
	if (lhs.matrix) delete []lhs.matrix;
	lhs.matrix = temp;
	return lhs;
}
inline Matrix operator * (const Matrix &lhs, const Matrix &rhs)
{ Matrix result(lhs); result *= rhs; return result; }
Matrix Matrix::transpose(const Matrix &matrix) {
	Matrix result(matrix.cols, matrix.rows);
	for (size_t i = 0; i < matrix.rows; ++i)
		for (size_t j = 0; j < matrix.cols; ++j)
			result.at(j, i) = matrix.at(i, j);
	return result;
}
Matrix Matrix::hadamard(const Matrix &lhs, const Matrix &rhs) {
	//assert(lhs.rows == rhs.rows && lhs.cols == rhs.cols);
	Matrix result(lhs);
	for (size_t i = 0; i < lhs.rows; ++i)
		for (size_t j = 0; j < lhs.cols; ++j)
			result.at(i, j) *= rhs.at(i, j);
	return result;
}
Matrix Matrix::kronecker(const Matrix &lhs, const Matrix &rhs) {
	Matrix result(lhs.rows * rhs.rows, lhs.cols * rhs.cols);
	for (size_t m = 0; m < lhs.rows; ++m)
		for (size_t n = 0; n < lhs.cols; ++n)
			for (size_t p = 0; p < rhs.rows; ++p)
				for (size_t q = 0; q < rhs.cols; ++q)
					result.at(m * rhs.rows + p, n * rhs.cols + q) = lhs.at(m, n) * rhs.at(p, q);
	return result;
}
Matrix Matrix::horcat(const Matrix &lhs, const Matrix &rhs) {
	//assert(lhs.rows == rhs.rows);
	Matrix result(lhs.rows, lhs.cols + rhs.cols);
	for (size_t i = 0; i < result.rows; ++i)
		for (size_t j = 0; j < result.cols; ++j)
			result.at(i, j) = j < lhs.cols ? lhs.at(i, j) : rhs.at(i, j - lhs.cols);
	return result;
}
Matrix Matrix::apply(const Matrix &matrix, function<double(double)> func) {
	Matrix result(matrix.rows, matrix.cols);
	for (size_t i = 0; i < matrix.rows; ++i)
		for (size_t j = 0; j < matrix.cols; ++j)
			result.at(i, j) = func(matrix.at(i, j));
	return result;
}


// Minimal Backpropagation neural network
// Require: initializer_list in <initializer_list>, exp in <cmath>, vector in <vector>, advance in <iterator>, tuple and make_tuple in <tuple>
//          random_device mt19937 uniform_real_distribution and uniform_int_distribution in <random>, ostream and istream in <iostream>
double activate(double x) {
	return 1.0 / (1.0 + exp(-x));
}
double activate_diff(double y) {
	return y * (1.0 - y);
}

struct NeuralLayer {
	Matrix weights;
	NeuralLayer() {}
	~NeuralLayer() {}
	NeuralLayer(size_t n_input, size_t n_output): weights(n_input + 1, n_output) { initialise_random(); }
	NeuralLayer(size_t n_input, size_t n_output, initializer_list<double> data): weights(n_input + 1, n_output, data) {}
	template<typename InputIterator>
	NeuralLayer(size_t n_input, size_t n_output, InputIterator first, InputIterator last): weights(n_input + 1, n_output, first, last) {}
	NeuralLayer(const NeuralLayer &orig): weights(orig.weights) {}
	NeuralLayer(NeuralLayer &&orig): weights(move(orig.weights)) {}
	NeuralLayer &operator = (const NeuralLayer &orig) { weights = orig.weights; return *this; }
	NeuralLayer &operator = (NeuralLayer &&orig) { weights = move(orig.weights); return *this; }
	static Matrix generate_bias(size_t row_count) { return Matrix(row_count, 1, 1.0); }
	void initialise_random() {
		static random_device rd;
		static mt19937 e2(rd());
		uniform_real_distribution<> dist(-0.5, 0.5);
		double factor = 0.7 * pow(weights.cols, 1.0 / weights.cols);
		for (size_t i = 0; i < weights.rows; ++i)
			for (size_t j = 0; j < weights.cols; ++j)
				weights.at(i, j) = dist(e2);
		double magnitude = weights.magnitude();
		for (size_t i = 0; i < weights.rows; ++i)
			for (size_t j = 0; j < weights.cols; ++j)
				weights.at(i, j) *= factor / magnitude;
	}
	Matrix feedforward(const Matrix &inputs) {
		return Matrix::apply(Matrix::horcat(inputs, generate_bias(inputs.rows)) * weights, [](double x){ return activate(x); });
	}
	Matrix backpropagation(const Matrix &input, const Matrix &output, const Matrix &error, double rate, int index = 0) {
		Matrix delta = Matrix::hadamard(Matrix::apply(output, [](double x) { return activate_diff(x); }), error), newerror;
		if (index) newerror = (delta * Matrix::transpose(weights)).submatrix(0, 1, 0, weights.rows - 1);
		weights += rate * Matrix::kronecker(Matrix::transpose(Matrix::horcat(input, generate_bias(1))), delta);
		return newerror;
	}
};
struct NeuralNet {
	static const double default_rate;
	double rate;
	vector<NeuralLayer> layers;
	NeuralNet(): rate(0.0) {}
	NeuralNet(initializer_list<size_t> size, double rate = default_rate): rate(rate)
	{ for (auto i = size.begin(), j = i; ++j != size.end(); ++i) layers.push_back(NeuralLayer(*i, *j)); }
	NeuralNet(initializer_list<size_t> size, initializer_list<double> data, double rate = default_rate): rate(rate) {
		initializer_list<double>::iterator first, last = data.begin();
		for (auto i = size.begin(), j = i; ++j != size.end(); ++i) {
			first = last;
			advance(last, (*i + 1) * *j);
			layers.push_back(NeuralLayer(*i, *j, first, last));
		}
	}
	NeuralNet(const NeuralNet &orig): rate(orig.rate), layers(orig.layers) {}
	NeuralNet(NeuralNet &&orig): rate(orig.rate), layers(move(orig.layers)) {}
	NeuralNet &operator = (const NeuralNet &orig) { rate = orig.rate; layers = orig.layers; return *this; }
	NeuralNet &operator = (NeuralNet &&orig) { rate = orig.rate; layers = move(orig.layers); return *this; }
	Matrix feedforward(const Matrix &inputs) {
		Matrix outputs(inputs);
		for (size_t i = 0; i < layers.size(); ++i)
			outputs = layers[i].feedforward(outputs);
		return outputs;
	}
	void train(const Matrix &input, const Matrix &output) {
		vector<Matrix> inputs;
		inputs.push_back(input);
		for (size_t i = 0; i < layers.size(); ++i)
			inputs.push_back(layers[i].feedforward(inputs.back()));
		Matrix error =  output - inputs.back();
		for (size_t i = layers.size(); i > 0 ; --i)
			error = layers[i - 1].backpropagation(inputs[i - 1], inputs[i], error, rate, i - 1);
	}
	void train(const Matrix &inputs, const Matrix &outputs, int num) {
		//assert(inputs.rows == outputs.rows);
		static random_device rd;
		static mt19937 e2(rd());
		uniform_int_distribution<size_t> dist(0, inputs.rows - 1);
		for (int i = 0; i < num; ++i) {
			size_t row = dist(e2);
			train(inputs.submatrix(row, row + 1, 0, inputs.cols),
			      outputs.submatrix(row, row + 1, 0, outputs.cols));
		}
	}
	void train(const Matrix &samples) {
		train(samples.submatrix(0, samples.rows, 0, layers.front().weights.rows - 1),
		      samples.submatrix(0, samples.rows, layers.front().weights.rows - 1, samples.cols));
	}
	void train(const Matrix &samples, int num) {
		train(samples.submatrix(0, samples.rows, 0, layers.front().weights.rows - 1),
		      samples.submatrix(0, samples.rows, layers.front().weights.rows - 1, samples.cols), num);
	}
	static Matrix classes_from_outputs(const Matrix &outputs) {
		Matrix result(outputs.rows, 1);
		for (size_t i = 0; i < outputs.rows; ++i) {
			size_t max = 0;
			for (size_t j = 1; j < outputs.cols; ++j)
				if (outputs.at(i, j) > outputs.at(i, max))
					max = j;
			result.at(i, 0) = max;
		}
		return result;
	}
	tuple<double, double> eval_network(const Matrix &inputs, const Matrix &target_outputs) {
		Matrix outputs = feedforward(inputs);
		Matrix temp = outputs - target_outputs;
		int c = 0;
		for (size_t i = 0; i < inputs.rows; ++i)
			if (static_cast<int>(classes_from_outputs(outputs).at(i, 0) + 0.5) != static_cast<int>(classes_from_outputs(target_outputs).at(i, 0) + 0.5))
				++c;
		return make_tuple(temp.magnitude() / temp.size(), static_cast<double>(c) / inputs.rows);
	}
	tuple<double, double> eval_network(const Matrix &samples) {
		return eval_network(samples.submatrix(0, samples.rows, 0, layers.front().weights.rows - 1),
		                    samples.submatrix(0, samples.rows, layers.front().weights.rows - 1, samples.cols));
	}
	void output_as_list(ostream &out) {
		out << "{ ";
		for (size_t i = 0; i < layers.size(); ++i)
			for (size_t j = 0; j < layers[i].weights.rows; ++j)
				for (size_t k = 0; k < layers[i].weights.cols; ++k) {
					out << layers[i].weights.at(j, k);
					if (i != layers.size() - 1 || j != layers[i].weights.rows - 1 || k != layers[i].weights.cols - 1)
						out << ", ";
				}
		out << " }" << endl;
	}
};
ostream &operator << (ostream &out, const NeuralNet &nn) {
	for (size_t i = 0; i < nn.layers.size(); ++i)
		out << nn.layers[i].weights;
	return out;
}
istream &operator >> (istream &in, NeuralNet &nn) {
	for (size_t i = 0; i < nn.layers.size(); ++i)
		in >> nn.layers[i].weights;
	return in;
}
const double NeuralNet::default_rate = 0.5;

int main(int args, const char *argv[])
{
	ifstream fin("iris_training.dat");
	Matrix samples(75, 7);
	fin >> samples;
	fin.close();
	Matrix samples1 = samples.submatrix(0, 50, 0, 7), samples2 = samples.submatrix(50, 75, 0, 7);

	int times = 100;
	double rate = 0.1;
	if (args >= 2) {
		istringstream timesstr(argv[1]);
		timesstr >> times;
		if (args >= 3) {
			istringstream ratestr(argv[2]);
			ratestr >> rate;
		}
	}

	NeuralNet nn({4, 5, 5, 3}, rate);
	//ifstream in("nerualnet.txt");
	//if (in) {
	//	in >> nn;
	//	in.close();
	//}

	for (int i = 0; i < times; ++i) {
		nn.train(samples1, 1000);
		auto error = nn.eval_network(samples2);
		cerr << "Regression Error: " << get<0>(error) << "\tClassification Error: " << get<1>(error) << "\tTimes: " << i << endl;
	}

	ofstream out("nerualnet.txt");
	if (out) {
		out << nn;
		out.close();
	}
	return 0;
}
