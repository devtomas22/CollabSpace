#!/bin/bash

# Check if a repository name was provided
if [ -z "$1" ]; then
  echo "Usage: $0 <repo-name>"
  exit 1
fi

name="$1"
dir="$HOME/gh/$name"

# Check if directory exists; if yes, stop
if [ -d "$dir" ]; then
  echo "Directory $dir already exists. Exiting."
  exit 1
fi

# Create the directory
mkdir -p "$dir" || { echo "Failed to create directory $dir"; exit 1; }

cd "$dir" || { echo "Failed to cd into $dir"; exit 1; }

# Initialize git repository
git init

# Create an initial README.md file
echo "# $name" > README.md

# Stage and commit the README.md
git add README.md
git commit -m "Initial commit with README.md"

# Create a private GitHub repository with the same name (no push)
gh repo create "$name" --private --confirm

# Link local repo to GitHub without pushing
git remote add origin "git@github.com:$(gh api user --jq .login)/$name.git"

echo "Repository $name created locally and on GitHub as private. You can now push your code as desired."
