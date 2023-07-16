// file_manager.h
#include "document.h"
#include <string>

class FileManager {
public:
    static bool save(const std::string& filename, const Document& doc);
    static bool load(const std::string& filename, Document& doc);
};
