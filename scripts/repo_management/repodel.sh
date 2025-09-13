#!/bin/bash
set -e

# Usage: ./delete_repo.sh repo_name

if [ $# -ne 1 ]; then
  echo "Usage: $0 <repo-name>"
  exit 1
fi

NAME=$1
REPO_DIR="$HOME/gh/$NAME"

# Remove local directory if it exists
if [ -d "$REPO_DIR" ]; then
  echo "Deleting directory $REPO_DIR"
  rm -rf "$REPO_DIR"
else
  echo "Local directory $REPO_DIR does not exist."
fi

# Delete GitHub repo using gh CLI
echo "Deleting GitHub repository $NAME"
gh repo delete "$(gh api user --jq .login)/$NAME" --yes

echo "Repository $NAME deleted locally and on GitHub."
