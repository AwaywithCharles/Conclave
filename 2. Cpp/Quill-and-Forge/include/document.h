// document.h
#include <vector>
#include <string>

class Document {
public:
    void edit(int line, const std::string& text);
    void append(const std::string& text);
    void print() const;

private:
    std::vector<std::string> lines;
};

