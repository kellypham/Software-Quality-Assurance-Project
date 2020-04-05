cmake_minimum_required(VERSION 2.8)
project(CSCI3060)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -g -Wall")

file(GLOB_RECURSE app_src
    "*.h"
    "*.cpp"
)

add_executable(Auctionr ${app_src})