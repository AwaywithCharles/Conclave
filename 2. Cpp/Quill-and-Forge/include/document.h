// document.h
#include <vector>
#include <string>
#include <ostream>

class Document {
public:
    void edit(int line, const std::string& text);
    void append(const std::string& text);
    void print(std::ostream& os) const;

private:
    std::vector<std::string> lines;
};

// document.cpp
#include "document.h"

void Document::edit(int line, const std::string& text) {
    if (line < lines.size()) {
        lines[line] = text;
    }
}

void Document::append(const std::string& text) {
    lines.push_back(text);
}

void Document::print(std::ostream& os) const {
    for (const auto& line : lines) {
        os << line << std::endl;
    }
}
