#!/opt/local/bin/python3.3

import csv

class Station(object) :

    def __init__(self, name, long, lat) :
        self.name = name
        self.long = long
        self.lat = lat

    def __hash__(self) :
        prime = 31
        result = 1 
        result = result * prime * hash(self.name)
        result = result * prime * hash(self.long)
        result = result * prime * hash(self.lat)
        return result

    def __eq__(self, o) :
        return self.name == o.name and self.long == o.long and self.lat == o.lat 
        

class Route(object) :
    def __init__(self, lineName, start, end) :
        self.lineName = lineName
        self.start = start
        self.end = end


def stations(path="stations.csv") :
    result = {}
    with open(path, "r") as stations_file :
        reader = csv.DictReader(stations_file)
        for row in reader :
            result[row["id"]] = \
                Station(row["name"],
                        float(row["longitude"]),
                        float(row["latitude"]))
    return result
        

def lines(path="lines.csv") :
    result = {}
    with open(path, "r") as lines_file :
        reader = csv.DictReader(lines_file)
        for row in reader :
            result[row["line"]] = row["name"]
    return result

def routes(stations, lines, path="routes.csv") :
    result = []
    with open(path, "r") as routes_file :
        reader = csv.DictReader(routes_file)
        for row in reader :
            start = stations[row["from"]]
            end = stations[row["to"]]
            line = lines[row["line"]]
            result.append(Route(line, start, end))
    return result

CYP_STATION_FORMAT = "CREATE (x {{name:'{name}', lat:{latitude:f}, long:{longitude:f}}});" 
CYP_LINK_FORMAT = "START starts=node(*), ends=node(*) WHERE starts.name='{startName}' AND ends.name='{endName}' CREATE starts-[r:LINK {{name:'{linkName}'}}]->ends;"

def to_cypher(routes) :
    stations = set()
    for route in routes :
        stations.add(route.start)
        stations.add(route.end)
    lines = []
    for station in stations :
        lines.append(CYP_STATION_FORMAT.format(name=station.name, latitude=station.lat, longitude=station.long))
    for route in routes :
        lines.append(CYP_LINK_FORMAT.format(startName=route.start.name, endName=route.end.name, linkName=route.lineName))
    return lines

def main() :
    output = to_cypher(routes(stations(), lines()))
    print("\n".join(output))

if __name__ == "__main__" :
    main()
